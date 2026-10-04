package com.jarvis.tickettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarvis.tickettracker.entity.TicketStage;

public interface TicketStageRepository extends JpaRepository<TicketStage, Long> {

}
