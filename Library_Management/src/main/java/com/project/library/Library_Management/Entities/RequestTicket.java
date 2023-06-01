package com.project.library.Library_Management.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity

public class RequestTicket {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
    private int studentId;
    private int bookId;
    private Date requestDate;
    private String status;
    
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public RequestTicket(int studentId, int bookId, Date requestDate, String status) {
		super();
		this.studentId = studentId;
		this.bookId = bookId;
		this.requestDate = requestDate;
		this.status = status;
	}
	public RequestTicket() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RequestTicket [studentId=" + studentId + ", bookId=" + bookId + ", requestDate=" + requestDate
				+ ", status=" + status + "]";
	}

    
}