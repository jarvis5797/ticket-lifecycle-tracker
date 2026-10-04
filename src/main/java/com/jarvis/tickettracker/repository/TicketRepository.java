package com.jarvis.tickettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarvis.tickettracker.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
