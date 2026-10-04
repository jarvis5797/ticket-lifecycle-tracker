package com.jarvis.tickettracker.entity;

import java.time.LocalDateTime;

import com.jarvis.tickettracker.enums.StageAction;
import com.jarvis.tickettracker.enums.StageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stage_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StageHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(
				name = "ticket_id",
				nullable = false
			)
	private Ticket ticket;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "stage_type", nullable = false)
	private StageType stageType;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StageAction action;
	
	@Column(columnDefinition = "TEXT")
	private String comment;
	
	@Column(nullable = false)
	private LocalDateTime timestamp;

}
