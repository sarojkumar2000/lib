package com.project.library.Library_Management.JpaControllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.library.Library_Management.Entities.Book;
import com.project.library.Library_Management.Entities.RequestTicket;
import com.project.library.Library_Management.Entities.Student;
import com.project.library.Library_Management.Entities.StudentBook;
import com.project.library.Library_Management.Repositories.BookRepository;
import com.project.library.Library_Management.Repositories.RequestTicketRepository;
import com.project.library.Library_Management.Repositories.StudentBookRepository;
import com.project.library.Library_Management.Repositories.StudentRepository;



@Controller
public class StudentControllerJpa {

	
public StudentControllerJpa(RequestTicketRepository ticketRepo,BookRepository bookrepo,StudentRepository studentRepo,StudentBookRepository sbRepo) {
		super();
		this.studentRepo=studentRepo;
		this.sbRepo=sbRepo;
		this.ticketRepo=ticketRepo;
		this.bookrepo=bookrepo;
		
	}
	private RequestTicketRepository ticketRepo;
	private StudentRepository studentRepo;
	private StudentBookRepository sbRepo;
	private BookRepository bookrepo;

	
	
	
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
		//This is will return the tickets raised by particular student(id)
		 @GetMapping("/requestTicketList") 
		  public String getBooksTakenByStudent(ModelMap model,@RequestParam int id) { 
			 List<RequestTicket> tickets = ticketRepo.findByStudentId(id);
			 if(tickets.isEmpty()) {
				 model.addAttribute("msg","NO requests");
			 }
			 
			 model.addAttribute("id",id);
			 model.addAttribute("tickets",tickets);
		  
		  return "requestTicketList"; 
		  }
		 //Adding requested book by student
		 @RequestMapping(value="/addRequestedBook",method=RequestMethod.GET)	 
		 public String addRequestedBook(ModelMap model,@RequestParam("id") int id ,@RequestParam("book_id") int book_id) {
			 Student student = studentRepo.findById(id);
			 Book book = bookrepo.findById(book_id);
			 List<StudentBook> books_taken=sbRepo.findByStudentId(id);//books taken by particular student Student_Book table
			 Book books = null;
			 List<Book> bookList = new ArrayList<>();
			 for(StudentBook studentbook:books_taken) { 
					
				   books=studentbook.getBook();
				   bookList.add(books);
		
			 }	 
			//Line 171 and 172 is the logic to add book to particulaer student 
			 student.getBooks().add(book);
			 List<Book> booksbystudent = student.getBooks();
			 
			 model.addAttribute("id",id);
			 model.addAttribute("booksbystudent",booksbystudent);
			 //This loop checks if student has already taken this Requested book in student_book table
			 int count = 0;
				String msg="";
				for (Book bookcount : bookList) {
				    if (bookcount.getBook_id() == book_id) {
				        count++;
				        if(count>0) {
				        	
				        	msg="This book is already taken by "+student.getName();
				        	model.addAttribute("msg",msg);
				        	return "redirect:/requestTicketList?id="+id;
				        }
				    }
				}
				//This Saves to Student_Book Table
				studentRepo.save(student);
				ticketRepo.deleteByBookId(book_id);
			 
			return "addingRequested"; 
		}
}
