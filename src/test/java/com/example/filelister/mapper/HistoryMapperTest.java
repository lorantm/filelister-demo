package com.example.filelister.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.filelister.dto.HistoryDTO;
import com.example.filelister.entity.HistoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class HistoryMapperTest {

    private HistoryMapper historyMapper;

    @BeforeEach
    public void setUp() {
        historyMapper = new HistoryMapper();
    }

    @Test
    public void testEntityToDTO() {
        long now = System.currentTimeMillis();
        HistoryEntity entity = new HistoryEntity(now, "user", "/test", ".dat");
        HistoryDTO dto = historyMapper.entityToDTO(entity);
        assertThat(dto).isNotNull();
        assertThat(dto.getTimestamp()).isEqualTo(now);
        assertThat(dto.getUsername()).isEqualTo("user");
        assertThat(dto.getDirectory()).isEqualTo("/test");
        assertThat(dto.getExtension()).isEqualTo(".dat");
    }

    @Test
    public void testDTOToEntity() {
        long now = System.currentTimeMillis();
        HistoryDTO dto = new HistoryDTO(now, "user", "/test", ".dat");
        HistoryEntity entity = historyMapper.dtoToEntity(dto);
        assertThat(entity).isNotNull();
        assertThat(entity.getTimestamp()).isEqualTo(now);
        assertThat(entity.getUsername()).isEqualTo("user");
        assertThat(entity.getDirectory()).isEqualTo("/test");
        assertThat(entity.getExtension()).isEqualTo(".dat");
    }
}