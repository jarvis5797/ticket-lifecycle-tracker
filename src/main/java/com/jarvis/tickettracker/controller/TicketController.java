package com.jarvis.tickettracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jarvis.tickettracker.dto.CreateTicketRequest;
import com.jarvis.tickettracker.dto.TicketResponse;
import com.jarvis.tickettracker.service.TicketService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
	
	private final TicketService ticketService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TicketResponse createTicket(@Valid @RequestBody CreateTicketRequest request) {
		return ticketService.createTicket(request);
	}
	
	@GetMapping("/{id}")
	public TicketResponse getTicket(@PathVariable Long id) {
		return ticketService.getTicket(id);
	}
	
	@GetMapping
	public List<TicketResponse> getTickets(@RequestParam String month) {
		return ticketService.getTicketsByMonth(month);
	}
	
	
	

}
