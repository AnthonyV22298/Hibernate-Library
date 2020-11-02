package com.ss.lms.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="tbl_book_genres")
@IdClass(com.ss.lms.Entity.BookGenreIds.class)
public class BookGenres implements Serializable{
	private static final long serialVersionUID = 3410831443135131978L;
	
	@Id
	@Column(name = "bookId")
	private Integer bookId;
	
	@Id
	@Column(name = "genre_id")
	private Integer genreId;
	
	public Integer getGenreId() {
		return genreId;
	}
	public void setGenreId(Integer id) {
		this.genreId = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int id) {
		this.bookId = id;
	}

}
