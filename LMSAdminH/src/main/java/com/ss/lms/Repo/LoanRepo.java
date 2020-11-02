package com.ss.lms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ss.lms.Entity.Loan;

public interface LoanRepo extends JpaRepository<Loan, Integer>{
	@Query(" FROM Loan where cardNo =:cardNo AND bookId =:bookId AND branchId =:branchId")
	public List<Loan> readLoansByIds(@Param("cardNo") int cardNo, @Param("bookId") int bookId, @Param("branchId") int branchId);
	
	@Query(" FROM Loan where bookId =:bookId")
	public List<Loan> readLoansByBookId(@Param("bookId") int bookId);
	
	@Query(" FROM Loan where branchId =:branchId")
	public List<Loan> readLoansByBranchId(@Param("branchId") int branchId);
	
	@Query(" FROM Loan where cardNo =:cardNo")
	public List<Loan> readLoansByCardNo(@Param("cardNo") int cardNo);
}
