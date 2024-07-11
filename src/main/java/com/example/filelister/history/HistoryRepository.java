package com.example.filelister.history;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Used for history database access.
 */
public interface HistoryRepository extends JpaRepository<HistoryEntry, Long> { }
