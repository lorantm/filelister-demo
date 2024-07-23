package com.example.filelister.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.filelister.entity.HistoryEntity;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class HistoryRepositoryTest {

    @Autowired
    private HistoryRepository historyRepository;

    @Test
    void testSaveAndRetrieveHistoryEntry() {
        long now = System.currentTimeMillis();
        HistoryEntity entry = new HistoryEntity(now, "testuser", "/test/path", ".txt");

        historyRepository.save(entry);

        List<HistoryEntity> entries = historyRepository.findAll();
        assertThat(entries).hasSize(1);
        assertEquals(entries.get(0).getTimestamp(), now);
        assertEquals(entries.get(0).getUsername(), "testuser");
        assertEquals(entries.get(0).getDirectory(), "/test/path");
        assertEquals(entries.get(0).getExtension(), ".txt");
    }
}