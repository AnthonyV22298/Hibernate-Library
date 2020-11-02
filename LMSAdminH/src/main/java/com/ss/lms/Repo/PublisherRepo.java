package com.ss.lms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ss.lms.Entity.Author;
import com.ss.lms.Entity.Publisher;

public interface PublisherRepo extends JpaRepository<Publisher, Integer>{
	@Query(" FROM Publisher where publisherName =:publisherName")
	public List<Publisher> readPublishersByTitle(@Param("publisherName") String publisherName);
}
