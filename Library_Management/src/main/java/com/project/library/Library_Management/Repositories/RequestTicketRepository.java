package com.project.library.Library_Management.Repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.library.Library_Management.Entities.RequestTicket;
@Transactional
public interface RequestTicketRepository extends JpaRepository<RequestTicket, Long> {

	List<RequestTicket> findByStudentId(int id);

	void deleteByStudentId(int id);

	void deleteByBookId(int id);

  
}
