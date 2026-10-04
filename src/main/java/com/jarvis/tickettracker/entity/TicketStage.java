package com.jarvis.tickettracker.entity;

import java.time.LocalDateTime;

import com.jarvis.tickettracker.enums.StageStatus;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket_stages", uniqueConstraints = {
		@UniqueConstraint(name = "uk_ticket_stage", columnNames = { "ticket_id", "stage_type" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketStage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ticket_id", nullable = false)
	private Ticket ticket;

	@Enumerated(EnumType.STRING)
	@Column(name = "stage_type", nullable = false)
	private StageType stageType;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StageStatus status;

	private LocalDateTime startedAt;

	private LocalDateTime completedAt;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();

		createdAt = now;
		updatedAt = now;
	}

	@PreUpdate
	protected void onUpdate() {

		updatedAt = LocalDateTime.now();
	}

}
