package com.ss.lms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ss.lms.Entity.Copies;

public interface CopiesRepo extends JpaRepository<Copies, Integer>{
	@Query(" FROM Copies where bookId =:bookId AND branchId =:branchId")
	public List<Copies> readCopiesByIds(@Param("bookId") int bookId, @Param("branchId") int branchId);
	
	@Query(" FROM Copies where bookId =:bookId")
	public List<Copies> readCopiesByBookId(@Param("bookId") int bookId);
	
	@Query(" FROM Copies where branchId =:branchId")
	public List<Copies> readCopiesByBranchId(@Param("branchId") int branchId);
}
