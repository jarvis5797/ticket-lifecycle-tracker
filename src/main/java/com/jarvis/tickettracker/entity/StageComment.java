package com.jarvis.tickettracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stage_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StageComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ticket_stage_id", nullable = false)
	private TicketStage ticketStage;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String comment;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}
}
