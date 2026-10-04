package com.jarvis.tickettracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarvis.tickettracker.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	boolean existsByBugIdAndMonth(String bugId, String month);

	List<Ticket> findByMonth(String month);

}
