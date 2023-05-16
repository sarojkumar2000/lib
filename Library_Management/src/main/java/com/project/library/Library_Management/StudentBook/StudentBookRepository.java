package com.project.library.Library_Management.StudentBook;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentBookRepository extends JpaRepository<StudentBook, Integer> {
	List<StudentBook> findByStudentId(int studentId);

}
