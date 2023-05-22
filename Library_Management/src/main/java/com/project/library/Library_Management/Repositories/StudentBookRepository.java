package com.project.library.Library_Management.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.library.Library_Management.Entities.StudentBook;

public interface StudentBookRepository extends JpaRepository<StudentBook, Integer> {
	List<StudentBook> findByStudentId(int studentId);
	
	
	
	//This Query retrieves full data from student_book table 
	
	 @Query("SELECT sb FROM StudentBook sb WHERE sb.student.id = :studentid AND sb.book.book_id = :book_id")
	    Optional<StudentBook> findByStudentIdAndBookId(@Param("studentid") int studentId, @Param("book_id") int bookId);

}
