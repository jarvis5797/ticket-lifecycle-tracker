package com.jarvis.tickettracker.dto;

import java.time.LocalDateTime;

import com.jarvis.tickettracker.enums.Priority;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TicketResponse {
	
	private Long id;
	
	private String bugId;
	
	private String title;
	
	private Priority priority;
	
	private String month;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
