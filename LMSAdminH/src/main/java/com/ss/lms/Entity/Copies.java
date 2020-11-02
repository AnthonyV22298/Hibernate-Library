package com.ss.lms.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="tbl_book_copies")
@IdClass(com.ss.lms.Entity.CopiesIds.class)
public class Copies implements Serializable{
	private static final long serialVersionUID = 3410831443135131978L;
	
	@Id
	@Column(name = "bookId")
	private Integer bookId;
	
	@Id
	@Column(name = "branchId")
	private Integer branchId;
	
	@Column(name = "noOfCopies")
	private int noOfCopies;
	
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
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int copies) {
		this.noOfCopies = copies;
	}
	public void addNoOfCopies(int copies) {
		this.noOfCopies += copies;
	}

}
