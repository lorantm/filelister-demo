package com.example.filelister.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.filelister.dto.HistoryDTO;
import com.example.filelister.mapper.HistoryMapper;
import com.example.filelister.repository.HistoryRepository;

/**
 * Service responsible for storing and retrieving history to and from the database.
 */
@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private HistoryMapper historyMapper;

    /**
     * Returns a list of HistoryDTOs.
     * @return a list of HistoryDTOs.
     */
    public List<HistoryDTO> getHistory() {
        return historyRepository.findAll().stream().map(historyMapper::entityToDTO).collect(Collectors.toList());
    }

    /**
     * Adds a new entry to the history database.
     * @param dto the HistoryDTO to add.
     */
    public void addHistory(HistoryDTO dto) {
        historyRepository.save(historyMapper.dtoToEntity(dto));
    }
}
