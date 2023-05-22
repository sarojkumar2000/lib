package com.project.library.Library_Management.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.library.Library_Management.Entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	Student findById(int id);


	List<Student> findByNameContainingIgnoreCase(String name);
	
}
