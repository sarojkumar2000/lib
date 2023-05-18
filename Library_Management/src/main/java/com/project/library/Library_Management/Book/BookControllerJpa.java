package com.project.library.Library_Management.Book;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.library.Library_Management.Student.Student;
import com.project.library.Library_Management.Student.StudentRepository;
import com.project.library.Library_Management.StudentBook.StudentBook;
import com.project.library.Library_Management.StudentBook.StudentBookRepository;

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

	@RequestMapping(value = "/bookslist")
	public String allBooks(ModelMap model) {
		List<Book> allbooks = bookrepo.findAll();

		model.addAttribute("allbooks", allbooks);
		return "Books";
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String addNewBook(ModelMap model) {
		Book book = new Book(0, "", "", "", "");
		model.put("book", book);
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

	@RequestMapping("delete-book")
	public String deleteBook(ModelMap model, @RequestParam int id) {
		bookrepo.deleteById(id);
		List<Book> allbooks = bookrepo.findAll();
		model.addAttribute("allbooks", allbooks);

		return "Books";
	}

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

		List<Book> books = bookrepo.findAll();

		model.addAttribute("student", student);
		model.addAttribute("books", books);

		return "addBookToStudent";
	}

	@PostMapping("/addBookToStudent")
	public String addBookToStudent(ModelMap model, @RequestParam int bookId, @RequestParam int studentId) {
		Student student = studentrepo.findById(studentId);
		Book book = bookrepo.findById(bookId);

		// Add the book to the student's list of books
		student.getBooks().add(book);
		studentrepo.save(student);
		int student_id = student.getId();
		List<Book> books = student.getBooks();
		model.addAttribute("student_id", student_id);
		model.addAttribute("books", books);

		return "bookstakenbystudent";

	}
	  @GetMapping("/bookstakenbystudent") 
	  public String getBooksTakenByStudent(ModelMap model,@RequestParam int id) { 
	
		List<StudentBook> books_taken=sbRepo.findByStudentId(id);
		System.out.println(books_taken);
		
		Book books = null;
		List<Book> bookList = new ArrayList<>();
		
		
		
		  for(StudentBook book:books_taken) { 
			   books=book.getBook();
			   bookList.add(books);
		  
		 }	  
	  int student_id=id;  
	  
	  
	  model.addAttribute("books",bookList);
	  model.addAttribute("student_id",student_id);
	
	  
	  return "bookstakenbystudent"; 
	  }
	  
	  //Borrow
	  
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
	 

	@RequestMapping("return")
	public String Return(ModelMap model, @RequestParam("book_id") int book_id,@RequestParam("studentid") int studentid) {
		Optional<StudentBook> studentBookOptional = sbRepo.findByStudentIdAndBookId(studentid, book_id);
		System.out.println(studentBookOptional.get());
		if (studentBookOptional.isPresent()) {
	        // Delete the StudentBook entity
	        sbRepo.delete(studentBookOptional.get());
	    }
	

		return "redirect:/bookstakenbystudent?id="+studentid;
	}

}
