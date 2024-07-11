package com.example.filelister.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.filelister.history.HistoryEntry;
import com.example.filelister.history.HistoryRepository;

/**
 * Spring REST controller that retrieves the file query history from the database.
 */
@RestController
public class HistoryController {

    @Autowired 
    private HistoryRepository historyRepository;

    /**
     * Retrieves the file query history from the database.
     * 
     * @return A list of HistoryEntry objects.
     */
    @GetMapping("/history")
    public List<HistoryEntry> getHistory() {
        return historyRepository.findAll();
    }
}
