package com.project.library.Library_Management.JpaControllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.library.Library_Management.Entities.Book;
import com.project.library.Library_Management.Entities.Student;
import com.project.library.Library_Management.Entities.StudentBook;
import com.project.library.Library_Management.Repositories.BookRepository;
import com.project.library.Library_Management.Repositories.StudentBookRepository;
import com.project.library.Library_Management.Repositories.StudentRepository;



@Controller
public class StudentControllerJpa {

	
public StudentControllerJpa(BookRepository bookrepo,StudentRepository studentRepo,StudentBookRepository sbRepo) {
		super();
		this.studentRepo=studentRepo;
		this.sbRepo=sbRepo;
	}

	private StudentRepository studentRepo;
	private StudentBookRepository sbRepo;
	
	
	
	//Adding New user
	@RequestMapping(value="/studentlogin",method=RequestMethod.GET)
	public String studentlogin() {
		
		return "studentlogin";
	}	
	@RequestMapping(value="/studentlogin",method=RequestMethod.POST)
	public String studentLogin(ModelMap model,@RequestParam("id") int id,@RequestParam("password") String password) {
		List<Student> allstudents= studentRepo.findAll();
		String errorMessage;
		String studentName =null; 
		
		
		for (Student student : allstudents) {
		    if (student.getId() == id) {
		    	String noBooksMsg;
		    	studentName=student.getName();
		    	List<StudentBook> books_taken=sbRepo.findByStudentId(id); 
				Book books = null;
				
				
				List<Book> bookList = new ArrayList<>();						
				for(StudentBook book:books_taken) { 
					   books=book.getBook();
					   bookList.add(books);
					   
				 }	    
				
				if (books_taken.isEmpty()) {	
					noBooksMsg="You have not taken any book.Visit library if you want to take.";
					
					model.addAttribute("studentName",studentName);
					model.addAttribute("noBooksMsg", noBooksMsg);
					return "student";
				}
				
				else {
					model.addAttribute("studentName",studentName);
					model.addAttribute("books",bookList);
					model.addAttribute("student_id",id);
					return "student";
				}
		        
		    }
		}
		errorMessage = "There is no id with "+id+ " ,Please contact Admin";
        model.addAttribute("errorMessage", errorMessage);	
		return "studentlogin";
	}
	
	
	
	//All students who are registered in library	
	@RequestMapping(value="/studentslist")
	public String allStudents(ModelMap model) {
		List<Student> allstudents= studentRepo.findAll();
		model.addAttribute("allstudents",allstudents);
		return "students";
	}
	
	
	
	//Adding New Student
	@RequestMapping(value="/addstudent",method=RequestMethod.GET)
	public String addNewStudent(ModelMap model) {
		//Student student=new Student(0,"","","");
		model.put("student", new Student());
		return "addstudent";
	}
	
	@RequestMapping(value="/addstudent",method=RequestMethod.POST)
	public String addStudent(ModelMap model,@Valid Student student,BindingResult result) {
		if(result.hasErrors()) {
			return "addstudent";
		}
		studentRepo.save(student);
		List<Student> allstudents=studentRepo.findAll();
		model.addAttribute("allstudents",allstudents);
		return "students";
	}
	
	
	//Search Book 
		@RequestMapping("searchStudent")
		public String searchBook(@RequestParam("student") String name,ModelMap model) {
			List<Student> allstudents = studentRepo.findByNameContainingIgnoreCase(name);
			System.out.println(allstudents);
			if(allstudents.isEmpty()) {
				String nostudentMsg="No student available,Please try again";
				model.addAttribute("nostudentMsg",nostudentMsg);
			}
			model.addAttribute("allstudents",allstudents);
			
			
			
			
			return "students";
		}
}
