package com.example.filelister.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.filelister.entity.HistoryEntity;

/**
 * Used for history database access.
 */
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> { }
