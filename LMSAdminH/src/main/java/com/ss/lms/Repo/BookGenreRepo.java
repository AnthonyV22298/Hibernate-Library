package com.ss.lms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ss.lms.Entity.BookGenres;

public interface BookGenreRepo extends JpaRepository<BookGenres, Integer>{
	@Query(" FROM BookGenres where bookId =:bookId")
	public List<BookGenres> readBookGenresById(@Param("bookId") int bookId);
	
	@Query(" FROM BookGenres where genreId =:genreId")
	public List<BookGenres> readBookGenresByGenreId(@Param("genreId") int genreId);

}
