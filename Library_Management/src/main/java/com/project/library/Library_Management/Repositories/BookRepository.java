package com.project.library.Library_Management.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.library.Library_Management.Entities.Book;
@Repository 
public interface BookRepository extends JpaRepository<Book, Integer>{
	List<Book> findByStudentsId(int studentId);

	 Book findById(int id);
	 
	List<Book> findByTitleContainingIgnoreCase(String book);

}
