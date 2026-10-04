package com.jarvis.tickettracker.service;

import java.net.Authenticator.RequestorType;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jarvis.tickettracker.dto.CreateTicketRequest;
import com.jarvis.tickettracker.dto.TicketResponse;
import com.jarvis.tickettracker.entity.Ticket;
import com.jarvis.tickettracker.entity.TicketStage;
import com.jarvis.tickettracker.enums.StageStatus;
import com.jarvis.tickettracker.enums.StageType;
import com.jarvis.tickettracker.repository.TicketRepository;
import com.jarvis.tickettracker.repository.TicketStageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {
	
	private final TicketRepository ticketRepository;
	private final TicketStageRepository ticketStageRepository;
	
	public TicketResponse createTicket(CreateTicketRequest request) {
		
		if(ticketRepository.existsByBugIdAndMonth(request.getBugId(), request.getMonth())) {
			
			throw new IllegalArgumentException("Ticket already exists for this month");
		}
		
		Ticket ticket  = Ticket.builder()
				.bugId(request.getBugId())
				.title(request.getTitle())
				.priority(request.getPriority())
				.month(request.getMonth())
				.build();
		
		Ticket savedTicket  = ticketRepository.save(ticket);
		
		createStage(savedTicket);
		
		return mapToResponse(savedTicket);
		
	}
	
	private void createStage(Ticket ticket) {
		
		for(StageType stageType : StageType.values()) {
			
			ticketStageRepository.save(TicketStage.builder()
						.ticket(ticket)
						.stageType(stageType)
						.status(StageStatus.NOT_STARTED)
						.build()
					);
		}
		
	}
	
	public TicketResponse getTicket(Long id) {
		
		Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket not found: "+ id));
		
		return mapToResponse(ticket);
	}
	
	public List<TicketResponse> getTicketsByMonth(String month) {
		List<Ticket> tickets = ticketRepository.findByMonth(month);
		
		return tickets.stream().map(ticket -> mapToResponse(ticket)).collect(Collectors.toList());
	}
	
	private TicketResponse mapToResponse(Ticket ticket) {
		
		return TicketResponse.builder()
				.id(ticket.getId())
				.bugId(ticket.getBugId())
				.title(ticket.getTitle())
				.priority(ticket.getPriority())
				.month(ticket.getMonth())
				.createdAt(ticket.getCreatedAt())
				.updatedAt(ticket.getUpdatedAt())
				.build();
	}
	

}
