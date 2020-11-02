package com.ss.lms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ss.lms.Entity.Author;
import com.ss.lms.Entity.Genre;

public interface GenreRepo extends JpaRepository<Genre, Integer>{
	@Query(" FROM Genre where genre_name =:genre_name")
	public List<Genre> readGenresByTitle(@Param("genre_name") String genreName);
}
