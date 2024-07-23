package com.example.filelister.mapper;

import org.springframework.stereotype.Component;

import com.example.filelister.dto.HistoryDTO;
import com.example.filelister.entity.HistoryEntity;

@Component
public class HistoryMapper {
    /**
     * Converts a HistorEntity to a HistoryDTO.
     * @param entity the HistoryEntity.
     * @return the HistoryDTO.
     */
    public HistoryDTO entityToDTO(HistoryEntity entity) {
        return new HistoryDTO(entity.getTimestamp(), entity.getUsername(), entity.getDirectory(), entity.getExtension());
    }

    /**
     * Converts a HistoryDTO to a
     * @param dto the HistoryDTO.
     * @return the HistoryEntity.
     */
    public HistoryEntity dtoToEntity(HistoryDTO dto) {
        return new HistoryEntity(dto.getTimestamp(), dto.getUsername(), dto.getDirectory(), dto.getExtension());
    }
}
