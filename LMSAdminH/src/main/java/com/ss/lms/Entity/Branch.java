package com.ss.lms.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_library_branch")
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branchId")
	private Integer branchId;
	
	@Column(name = "branchName")
	private String branchName;
	
	@Column(name = "branchAddress")
	private String branchAddress;
	
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "branches")
	@JsonBackReference
	private List<Book> books;
	
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer id) {
		this.branchId = id;
	}
	public String getName() {
		return branchName;
	}
	public void setName(String name) {
		this.branchName = name;
	}
	public String getAddress() {
		return branchAddress;
	}
	public void setAddress(String address) {
		this.branchAddress = address;
	}

}
