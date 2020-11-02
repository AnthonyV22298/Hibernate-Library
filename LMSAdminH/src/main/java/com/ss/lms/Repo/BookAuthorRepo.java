package com.ss.lms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ss.lms.Entity.BookAuthors;

public interface BookAuthorRepo extends JpaRepository<BookAuthors, Integer>{
	@Query(" FROM BookAuthors where bookId =:bookId")
	public List<BookAuthors> readCopiesById(@Param("bookId") int bookId);
	
	@Query(" FROM BookAuthors where authorId =:authorId")
	public List<BookAuthors> readCopiesByAuthorId(@Param("authorId") int authorId);

}
