package com.project.library.Library_Management.JpaControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.project.library.Library_Management.Entities.RequestTicket;
import com.project.library.Library_Management.Repositories.RequestTicketRepository;
import com.project.library.Library_Management.Repositories.StudentRepository;

@Controller
@RequestMapping("/request-tickets")
public class RequestTicketController {
	@Autowired
    public RequestTicketController(RestTemplate restTemplate,StudentRepository studentrepo,RequestTicketRepository ticketRepo) {
        this.restTemplate = restTemplate;
        this.studentrepo=studentrepo;
        this.ticketRepo=ticketRepo;
        }
	
	
	 private final RestTemplate restTemplate;
	    private final String BASE_URL = "http://localhost:8081/request-tickets";
	    private StudentRepository studentrepo;
	    private RequestTicketRepository ticketRepo;
	    
	    
	    @RequestMapping(value="/create",method=RequestMethod.GET)
	    public String showCreateForm(ModelMap model) {
	        model.addAttribute("requestTicket", new RequestTicket());
	        return "createRequestTicketForm";
	    }
	    @PostMapping("/create")
	    public String createRequestTicket(@ModelAttribute("requestTicket") RequestTicket requestTicket) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Type", "application/json");

	        HttpEntity<RequestTicket> requestEntity = new HttpEntity<>(requestTicket, headers);
	        ResponseEntity<RequestTicket> responseEntity = restTemplate.exchange(
	                BASE_URL,
	                HttpMethod.POST,
	                requestEntity,
	                RequestTicket.class
	        );
	        RequestTicket ticket = requestEntity.getBody(); // Get the RequestTicket from the RequestEntity
	      ticketRepo.save(ticket);
	      
	        
	        
	       
	        return "TicketResponseToStudent";
	        
	        
	    }
		/*
		 * @GetMapping public String getAllRequestTickets(ModelMap model) {
		 * ResponseEntity<RequestTicket[]> responseEntity =
		 * restTemplate.getForEntity(BASE_URL, RequestTicket[].class); RequestTicket[]
		 * requestTickets = responseEntity.getBody();
		 * System.out.println(requestTickets);
		 * 
		 * 
		 * model.addAttribute("requestTickets", requestTickets); return
		 * "requestTicketList"; }
		 */
		 

}
