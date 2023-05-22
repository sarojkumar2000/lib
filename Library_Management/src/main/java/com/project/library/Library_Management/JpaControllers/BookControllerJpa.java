package com.project.library.Library_Management.JpaControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class BookControllerJpa {

	public BookControllerJpa(BookRepository bookrepo, StudentRepository studentrepo, StudentBookRepository sbRepo) {
		super();

		this.bookrepo = bookrepo;
		this.studentrepo = studentrepo;
		this.sbRepo = sbRepo;
	}

	private BookRepository bookrepo;
	private StudentRepository studentrepo;
	private StudentBookRepository sbRepo;
	//All book available in library
	@RequestMapping(value = "/bookslist")
	public String allBooks(ModelMap model) {
		List<Book> allbooks = bookrepo.findAll();

		model.addAttribute("allbooks", allbooks);
		return "Books";
	}
	//Adding book to library
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String addNewBook(ModelMap model) {
		
		model.put("book", new Book());
		return "addbook";
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String addStudent(ModelMap model, @Valid Book book, BindingResult result) {
		if (result.hasErrors()) {
			return "addbook";
		}
		bookrepo.save(book);
		/*
		 * bookservice.addNewbooks(book.getId(),book.getTitle(),book.getAuthor(),book.
		 * getPublisher(),book.getNo_of_available());
		 */
		List<Book> allbooks = bookrepo.findAll();
		model.addAttribute("allbooks", allbooks);
		return "Books";
	}
	//Delete Book

	@RequestMapping("delete-book")
	public String deleteBook(ModelMap model, @RequestParam int id) {
		bookrepo.deleteById(id);
		List<Book> allbooks = bookrepo.findAll();
		model.addAttribute("allbooks", allbooks);

		return "Books";
	}
	//Updating Book details
	@RequestMapping(value = "/update-book", method = RequestMethod.GET)
	public String showUpdateBookPage(ModelMap model, @RequestParam int id) {
		Book book = bookrepo.findById(id);

		model.addAttribute("book", book);

		return "addbook";
	}

	@RequestMapping(value = "/update-book", method = RequestMethod.POST)
	public String updatingBook(ModelMap model, @Valid Book book, BindingResult result) {
		if (result.hasErrors()) {
			return "addbook";
		}
		bookrepo.deleteById(book.getBook_id());
		bookrepo.save(book);
		/*
		 * bookservice.addNewbooks(book.getId(),book.getTitle(),book.getAuthor(),book.
		 * getPublisher(),book.getNo_of_available());
		 */
		List<Book> allbooks = bookrepo.findAll();
		model.addAttribute("allbooks", allbooks);
		return "Books";
	}

	// Adding book to individual Student

	@RequestMapping(value = "/addBookToStudent", method = RequestMethod.GET)
	public String addBookToStudent(ModelMap model, @RequestParam int id) {
		Student student = studentrepo.findById(id);
		String studentName=student.getName();
		List<Book> books = bookrepo.findAll();
		
		model.addAttribute("student", student);
		model.addAttribute("studentName",studentName);
		model.addAttribute("books", books);

		return "addBookToStudent";
	}
	// Add the book to the student's list of books
	@PostMapping("/addBookToStudent")
	public String addBookToStudent(ModelMap model, @RequestParam int bookId, @RequestParam int studentId) {
		Student student = studentrepo.findById(studentId);
		Book book = bookrepo.findById(bookId);
		
		
		
		
		
		
		
		List<StudentBook> books_taken=sbRepo.findByStudentId(studentId);
		Book books = null;
		List<Book> bookList = new ArrayList<>();	
		for(StudentBook studentbook:books_taken) { 
			
			   books=studentbook.getBook();
			   bookList.add(books);
	
		 }	 
		
		student.getBooks().add(book);
		
		int student_id = student.getId();
		List<Book> booksbystudent = student.getBooks();
		model.addAttribute("student_id", student_id);
		model.addAttribute("books", booksbystudent);
		
		int count = 0;
		String msg=" ";
		String studentName=student.getName();
		
		for (Book bookcount : bookList) {
		    if (bookcount.getBook_id() == bookId) {
		        count++;
		        if(count>0) {
		        	msg="This book is already taken by "+student.getName();
		        	model.addAttribute("msg",msg);
		        	model.addAttribute("student_id", student_id);
		        	model.addAttribute("studentName", studentName);
		    		model.addAttribute("books", booksbystudent);
		    		return "addBookToStudent";
		        	//return "redirect:/bookstakenbystudent?id="+studentId;
		        }
		    }
		}

		

		
		

		studentrepo.save(student);
		
		model.addAttribute("student_id", student_id);
    	model.addAttribute("studentName", studentName);
		model.addAttribute("books", booksbystudent);
		
		
		

		return "bookstakenbystudent";

	}
	
	//Books taken by Individual student
	  @GetMapping("/bookstakenbystudent") 
	  public String getBooksTakenByStudent(ModelMap model,@RequestParam int id) { 
	
		List<StudentBook> books_taken=sbRepo.findByStudentId(id);
		List<Student> allstudents= studentrepo.findAll();
		
		
		Book books = null;
		List<Book> bookList = new ArrayList<>();	
		for(StudentBook book:books_taken) { 
			
			   books=book.getBook();
			   bookList.add(books);
	
		 }	  
		
		
		
		
		
		
		
		String studentName=null;
		//This loop is to get student Name Only
		for (Student student : allstudents) {
		    if (student.getId() == id) {
		    	 studentName=student.getName();		    	
		    	break;
		    }
		}
	  
	  
	  model.addAttribute("books",bookList);
	  model.addAttribute("student_id",id);
	  model.addAttribute("studentName",studentName);
	
	  
	  return "bookstakenbystudent"; 
	  }
	  
	  //All books taken by students
	  
	  @GetMapping("/BorrowedBooks") 
	  public String getBorrwedBooks(ModelMap model) { 
		  List<StudentBook> books_taken=sbRepo.findAll(); 
		  model.addAttribute("books_taken", books_taken);
			 
	
			
	  
	  return "borrowedBooks"; 
	  }

	
	/*
	 * @GetMapping("/bookstakenbystudent") public String
	 * getBooksTakenByStudent(ModelMap model,@RequestParam int id) { List<Book>
	 * books = bookrepo.findByStudentsId(id);
	 * 
	 * int student_id=id;
	 * 
	 * 
	 * model.addAttribute("student_id",student_id);
	 * model.addAttribute("books",books);
	 * 
	 * return "bookstakenbystudent"; }
	 */
	 
	  //When student returns the book in library
	@RequestMapping("return")
	public String Return(ModelMap model, @RequestParam("book_id") int book_id,@RequestParam("studentid") int studentid) {
		Optional<StudentBook> studentBookOptional = sbRepo.findByStudentIdAndBookId(studentid, book_id);
		
		if (studentBookOptional.isPresent()) {
	        // Delete the StudentBook entity
	        sbRepo.delete(studentBookOptional.get());
	    }
	

		return "redirect:/bookstakenbystudent?id="+studentid;
	}
	
	//Search Book 
	@RequestMapping("search")
	public String searchBook(@RequestParam("book") String book,ModelMap model) {
		List<Book> allbooks = bookrepo.findByTitleContainingIgnoreCase(book);
		if(allbooks.isEmpty()) {
			String noBooksMsg="No books available";
			model.addAttribute("noBooksMsg",noBooksMsg);
		}
		model.addAttribute("allbooks",allbooks);
		
		
		
		
		return "Books";
	}

}
