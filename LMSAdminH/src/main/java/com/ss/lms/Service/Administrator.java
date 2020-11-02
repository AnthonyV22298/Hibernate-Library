package com.ss.lms.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ss.lms.Entity.*;
import com.ss.lms.Repo.*;

@RestController
public class Administrator {
	
	@Autowired
	BookRepo brepo;
	
	@Autowired
	AuthorRepo arepo;
	
	@Autowired
	PublisherRepo prepo;
	
	@Autowired
	GenreRepo grepo;
	
	@Autowired
	BranchRepo brrepo;
	
	@Autowired
	BorrowerRepo borepo;
	
	@Autowired
	CopiesRepo crepo;
	
	@Autowired
	LoanRepo lrepo;
	
	@Autowired
	BookAuthorRepo barepo;
	
	@Autowired
	BookGenreRepo bgrepo;
	
	@RequestMapping(value = "/getBooks", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooks(String searchString) {
		List<Book> books = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
			books = brepo.readBooksByTitle(searchString);
		} else {
			books = brepo.findAll();
		}
		return books;
	}
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public void addBook(@RequestParam String title, @RequestParam int pubId, @RequestParam int[] authorIds, @RequestParam int[] genreIds, @RequestParam int[] branchIds) {
		Book book = new Book();
		book.setTitle(title);
		book.setPublisher(prepo.findById(pubId).get());
		brepo.save(book);

		int bookId = brepo.readBooksByTitle(title).get(0).getBookId();
		
		for(Integer id : authorIds) {
			BookAuthors entity = new BookAuthors();
			entity.setBookId(bookId);
			entity.setAuthorId(id);
			barepo.save(entity);
		}
		
		for(Integer id : genreIds) {
			BookGenres entity = new BookGenres();
			entity.setBookId(bookId);
			entity.setGenreId(id);
			bgrepo.save(entity);
		}
		
		for(Integer id : branchIds) {
			Copies entity = new Copies();
			entity.setBookId(bookId);
			entity.setBranchId(id);
			entity.setNoOfCopies(1);
			crepo.save(entity);
		}
	}
	
	@RequestMapping(value = "/updateBook", method = RequestMethod.PUT)
	public void updateBook(@RequestParam String oldTitle, @RequestParam String newTitle) {
		Book book = brepo.readBooksByTitle(oldTitle).get(0);
		
		if(newTitle != null) {
			book.setTitle(newTitle);
		}
		brepo.save(book);
	}
	
	@RequestMapping(value = "/deleteBook", method = RequestMethod.DELETE)
	public boolean deleteBook(@RequestParam int bookId) {
		boolean flag = true;
		
		List<Loan> lList = new ArrayList<>();
		lList = lrepo.readLoansByBookId(bookId);
		
		for(Loan entity : lList) {
			if(entity.getDateIn() != null) {
				lrepo.delete(entity);
			}
			else {
				flag = false;
				System.out.println("Book is checked out, can't be deleted");
				return flag;
			}
		}
		
		if(flag) {
			List<BookAuthors> baList = new ArrayList<>();
			baList = barepo.readCopiesById(bookId);
		
			for(BookAuthors entity : baList) {
				barepo.delete(entity);
			}
		
			List<BookGenres> bgList = new ArrayList<>();
			bgList = bgrepo.readBookGenresById(bookId);
		
			for(BookGenres entity : bgList) {
				bgrepo.delete(entity);
			}
		
			List<Copies> cList = new ArrayList<>();
			cList = crepo.readCopiesByBookId(bookId);
		
			for(Copies entity : cList) {
				crepo.delete(entity);
			}
		
			brepo.deleteById(bookId);
		}
		return flag;
	}
	
	@RequestMapping(value = "/getAuthors", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAuthors(@RequestParam String searchString) {
		List<Author> authors = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
			authors = arepo.readAuthorsByTitle(searchString);
		} else {
			authors = arepo.findAll();
		}
		return authors;
	}
	
	@RequestMapping(value = "/addAuthor", method = RequestMethod.POST)
	public void addAuthor(@RequestParam String authorName) {
		Author author = new Author();
		author.setAuthorName(authorName);
		arepo.save(author);
	}
	
	@RequestMapping(value = "/updateAuthor", method = RequestMethod.PUT)
	public void updateAuthor(@RequestParam String oldName, @RequestParam String newName) {
		Author author = arepo.readAuthorsByTitle(oldName).get(0);
		author.setAuthorName(newName);
		arepo.save(author);
	}
	
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.DELETE)
	public void deleteAuthor(@RequestParam int authorId) {
		List<BookAuthors> baList = new ArrayList<>();
		baList = barepo.readCopiesByAuthorId(authorId);
	
		for(BookAuthors entity : baList) {
			barepo.delete(entity);
		}
		
		brepo.deleteById(authorId);
	}
	
	@RequestMapping(value = "/getPublishers", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getPublishers(@RequestParam String searchString) {
		List<Publisher> publishers = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
			publishers = prepo.readPublishersByTitle(searchString);
		} else {
			publishers = prepo.findAll();
		}
		return publishers;
	}
	
	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST)
	public void addPublisher(@RequestParam String publisherName, @RequestParam String publisherAddress) {
		Publisher publisher = new Publisher();
		publisher.setName(publisherName);
		publisher.setAddress(publisherAddress);
		prepo.save(publisher);
	}
	
	@RequestMapping(value = "/updatePublisher", method = RequestMethod.PUT)
	public void updatePublisher(@RequestParam String oldName, @RequestParam String newName, @RequestParam String address) {
		Publisher publisher = prepo.readPublishersByTitle(oldName).get(0);
		
		publisher.setName(newName);
		publisher.setAddress(address);
		prepo.save(publisher);
	}
	
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.DELETE)
	public void deletePublisher(@RequestParam int publisherId) {
		List<Book> books = brepo.readBooksByPubId(publisherId);
		
		for(Book book : books) {
			if(!deleteBook(book.getBookId())) {
				return;
			}
		}
		prepo.deleteById(publisherId);
	}
	
	@RequestMapping(value = "/getGenres", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getGenres(@RequestParam String searchString) {
		List<Genre> genres = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
			genres = grepo.readGenresByTitle(searchString);
		} else {
			genres = grepo.findAll();
		}
		return genres;
	}
	
	@RequestMapping(value = "/addGenre", method = RequestMethod.POST)
	public void addGenre(@RequestParam String genreName) {
		Genre genre = new Genre();
		genre.setName(genreName);
		grepo.save(genre);
	}
	
	@RequestMapping(value = "/updateGenre", method = RequestMethod.PUT)
	public void updateGenre(@RequestParam String oldName, @RequestParam String newName) {
		Genre genre = grepo.readGenresByTitle(oldName).get(0);
		
		genre.setName(newName);
		grepo.save(genre);
	}
	
	@RequestMapping(value = "/deleteGenre", method = RequestMethod.DELETE)
	public void deleteGenre(@RequestParam int genreId) {
		List<BookGenres> bgList = new ArrayList<>();
		bgList = bgrepo.readBookGenresById(genreId);
	
		for(BookGenres entity : bgList) {
			bgrepo.delete(entity);
		}
		grepo.deleteById(genreId);
	}
	
	@RequestMapping(value = "/getBranches", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getBranches(@RequestParam String searchString) {
		List<Branch> branches = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
			branches = brrepo.readBranchesByTitle(searchString);
		} else {
			branches = brrepo.findAll();
		}
		return branches;
	}
	
	@RequestMapping(value = "/addBranch", method = RequestMethod.POST)
	public void addBranch(@RequestParam String branchName, @RequestParam String branchAddress) {
		Branch branch = new Branch();
		branch.setName(branchName);
		branch.setAddress(branchAddress);
		brrepo.save(branch);
	}
	
	@RequestMapping(value = "/updateBranch", method = RequestMethod.PUT)
	public void updateBranch(@RequestParam String oldName, @RequestParam String newName, @RequestParam String address) {
		Branch branch = brrepo.readBranchesByTitle(oldName).get(0);
		
		branch.setName(newName);
		branch.setAddress(address);
		brrepo.save(branch);
	}
	
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.DELETE)
	public void deleteBranch(@RequestParam int branchId) {
		boolean flag = true;
		
		List<Loan> lList = new ArrayList<>();
		lList = lrepo.readLoansByBranchId(branchId);
		
		for(Loan entity : lList) {
			if(entity.getDateIn() != null) {
				lrepo.delete(entity);
			}
			else {
				flag = false;
				System.out.println("Book is checked out, can't be deleted");
				return;
			}
		}
		
		if(flag) {
			List<Copies> cList = new ArrayList<>();
			cList = crepo.readCopiesByBranchId(branchId);
		
			for(Copies entity : cList) {
				crepo.delete(entity);
			}
		
		}
		brrepo.deleteById(branchId);
	}
	
	@RequestMapping(value = "/getBorrowers", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getBorrowers(@RequestParam String searchString) {
		List<Borrower> borrowers = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
			borrowers = borepo.readBorrowersByTitle(searchString);
		} else {
			borrowers = borepo.findAll();
		}
		return borrowers;
	}
	
	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST)
	public void addBorrower(@RequestParam String borrowerName, @RequestParam String borrowerAddress) {
		Borrower borrower = new Borrower();
		borrower.setName(borrowerName);
		borrower.setAddress(borrowerAddress);
		borepo.save(borrower);
	}
	
	@RequestMapping(value = "/updateBorrower", method = RequestMethod.PUT)
	public void updateBorrower(@RequestParam String oldName, @RequestParam String newName, @RequestParam String address) {
		Borrower borrower = borepo.readBorrowersByTitle(oldName).get(0);
		
		borrower.setName(newName);
		borrower.setAddress(address);
		borepo.save(borrower);
	}
	
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.DELETE)
	public void deleteBorrower(@RequestParam int cardNo) {
		List<Loan> lList = new ArrayList<>();
		lList = lrepo.readLoansByCardNo(cardNo);
		
		for(Loan entity : lList) {
			if(entity.getDateIn() != null) {
				lrepo.delete(entity);
			}
			else {
				System.out.println("Book is checked out, can't be deleted");
				return;
			}
		}
		borepo.deleteById(cardNo);
	}
	
}
