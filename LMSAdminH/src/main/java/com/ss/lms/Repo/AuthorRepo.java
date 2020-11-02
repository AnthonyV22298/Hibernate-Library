package com.ss.lms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ss.lms.Entity.Author;
import com.ss.lms.Entity.Book;

public interface AuthorRepo extends JpaRepository<Author, Integer>{
	@Query(" FROM Author where authorName =:authorName")
	public List<Author> readAuthorsByTitle(@Param("authorName") String authorName);
}
