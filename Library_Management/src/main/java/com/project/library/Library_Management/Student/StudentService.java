package com.project.library.Library_Management.Student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class StudentService {


	private static List<Student>students= new ArrayList<>();
	
static {
		
		students.add(new Student(101,"saroj","Btech","CSE"));
		students.add(new Student(102,"Mani","Btech", "CSE"));
		students.add(new Student(103,"Anusha","Btech", "CSE"));
		students.add(new Student(104,"Vidya","Btech", "CSE"));
	}

public List<Student> AllStudents(String string) {
	
	return students;
}

}