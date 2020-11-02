package com.ss.lms.Entity;


import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




/**
 * @author ppradhan
 *
 */
@Entity
@Table(name="tbl_book")
public class Book{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookId")
	private Integer bookId;
	
	@Column(name = "title")
	private String title;
	
	@ManyToOne()
	@JoinColumn(name="pubId", referencedColumnName = "publisherId", insertable = false, updatable = false)
	private Publisher publisher;
	
	@ManyToMany
	@JoinTable(name = "tbl_book_authors", joinColumns = {@JoinColumn(name="bookId") }, 
		inverseJoinColumns = {@JoinColumn(name="authorId") } )
	private List<Author> authors;
	
	@ManyToMany
	@JoinTable(name = "tbl_book_genres", joinColumns = {@JoinColumn(name="bookId") }, 
		inverseJoinColumns = {@JoinColumn(name="genre_id") } )
	private List<Genre> genres;
	
	@ManyToMany
	@JoinTable(name = "tbl_book_copies", joinColumns = {@JoinColumn(name="bookId") }, 
		inverseJoinColumns = {@JoinColumn(name="branchId") } )
	private List<Branch> branches;
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher optional) {
		this.publisher = optional;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public List<Branch> getBranches() {
		return branches;
	}
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
}
