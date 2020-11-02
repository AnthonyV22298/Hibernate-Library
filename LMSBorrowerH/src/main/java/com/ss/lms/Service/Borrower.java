package com.ss.lms.Service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.Entity.Copies;
import com.ss.lms.Entity.Loan;
import com.ss.lms.Repo.CopiesRepo;
import com.ss.lms.Repo.LoanRepo;

@RestController
public class Borrower {
	
	@Autowired
	LoanRepo lrepo;
	
	@Autowired
	CopiesRepo crepo;
	
	@RequestMapping(value = "/getLoans", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> getLoans(@RequestParam int cardNo, @RequestParam int bookId, @RequestParam int branchId) {
		List<Loan> loans = new ArrayList<>();
		if (cardNo >0 && bookId > 0 && branchId > 0) {
			loans = lrepo.readLoansByIds(cardNo, bookId, branchId);
		} else {
			loans = lrepo.findAll();
		}
		return loans;
	}
	
	@RequestMapping(value = "/returnLoan", method = RequestMethod.PUT)
	public void returnLoan(@RequestParam int cardNo, @RequestParam int bookId, @RequestParam int branchId) {
		Loan loan = lrepo.readLoansByIds(cardNo, bookId, branchId).get(0);
		loan.setDateIn(new Date());
		lrepo.save(loan);
		
		Copies copies = crepo.readCopiesByIds(bookId, branchId).get(0);
		copies.addNoOfCopies(1);
		crepo.save(copies);
	}
	
	@RequestMapping(value = "/addLoan", method = RequestMethod.POST)
	public void addLoan(@RequestParam int cardNo, @RequestParam int bookId, @RequestParam int branchId) {
		Loan loan = new Loan();
		loan.setBookId(bookId);
		loan.setBranchId(branchId);
		loan.setCardNo(cardNo);
		loan.setDateOut(new Date());
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date dueDate = cal.getTime();
		loan.setDueDate(dueDate);
		lrepo.save(loan);
		
		Copies copies = crepo.readCopiesByIds(bookId, branchId).get(0);
		copies.addNoOfCopies(-1);
		crepo.save(copies);
	}
}
