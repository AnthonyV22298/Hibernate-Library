package com.ss.lms.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_publisher")
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "publisherId")
	private Integer publisherId;
	
	@Column(name = "publisherName")
	private String publisherName;
	
	@Column(name = "publisherAddress")
	private String publisherAddress;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "authors")
	@JsonBackReference
	private List<Book> books;
	
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer id) {
		this.publisherId = id;
	}
	public String getName() {
		return publisherName;
	}
	public void setName(String name) {
		this.publisherName = name;
	}
	public String getAddress() {
		return publisherAddress;
	}
	public void setAddress(String address) {
		this.publisherAddress = address;
	}
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
