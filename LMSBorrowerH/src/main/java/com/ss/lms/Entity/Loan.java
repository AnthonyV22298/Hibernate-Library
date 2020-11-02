package com.ss.lms.Entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="tbl_book_loans")
@IdClass(com.ss.lms.Entity.LoanIds.class)
public class Loan {
private static final long serialVersionUID = 3410831443135131978L;

	@Id
	@Column(name = "cardNo")
	private Integer cardNo;
	
	@Id
	@Column(name = "bookId")
	private Integer bookId;
	
	@Id
	@Column(name = "branchId")
	private Integer branchId;
	
	@Column(name = "dateOut")
	private Date dateOut;
	
	@Column(name = "dueDate")
	private Date dueDate;
	
	@Column(name = "dateIn")
	private Date dateIn;
	
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer id) {
		this.branchId = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int id) {
		this.bookId = id;
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int id) {
		this.cardNo = id;
	}
	public Date getDateOut() {
		return dateOut;
	}
	public void setDateOut(Date date) {
		this.dateOut = date;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date date) {
		this.dueDate = date;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date date) {
		this.dateIn = date;
	}
}
