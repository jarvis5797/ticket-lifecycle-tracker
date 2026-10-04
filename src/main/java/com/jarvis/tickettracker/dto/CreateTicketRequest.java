package com.jarvis.tickettracker.dto;

import com.jarvis.tickettracker.enums.Priority;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTicketRequest {
	
	@NotBlank
	private String bugId;
	
	@NotBlank
	private String title;
	
	private Priority priority;
	
	@NotBlank
	private String month;

}
