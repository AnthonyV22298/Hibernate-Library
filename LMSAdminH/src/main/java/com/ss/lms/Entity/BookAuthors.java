package com.ss.lms.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="tbl_book_authors")
@IdClass(com.ss.lms.Entity.BookAuthorIds.class)
public class BookAuthors implements Serializable{
	private static final long serialVersionUID = 3410831443135131978L;
	
	@Id
	@Column(name = "bookId")
	private Integer bookId;
	
	@Id
	@Column(name = "authorId")
	private Integer authorId;
	
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer id) {
		this.authorId = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int id) {
		this.bookId = id;
	}

}
