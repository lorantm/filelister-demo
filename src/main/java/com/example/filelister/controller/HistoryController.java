package com.example.filelister.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.filelister.dto.HistoryDTO;
import com.example.filelister.service.HistoryService;

/**
 * Spring REST controller that retrieves the file query history from the database.
 */
@RestController
public class HistoryController {

    @Autowired 
    private HistoryService historyService;

    /**
     * Retrieves the file query history from the database.
     * 
     * @return A list of HistoryEntry objects.
     */
    @GetMapping("/history")
    public List<HistoryDTO> getHistory() {
        return historyService.getHistory();
    }
}
