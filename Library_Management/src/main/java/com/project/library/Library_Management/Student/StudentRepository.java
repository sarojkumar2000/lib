package com.project.library.Library_Management.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	Student findById(int id);

}
