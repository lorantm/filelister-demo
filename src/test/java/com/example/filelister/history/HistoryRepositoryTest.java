package com.example.filelister.history;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
        HistoryEntry entry = new HistoryEntry();

        long now = System.currentTimeMillis();
        entry.setTimestamp(now);
        entry.setUsername("testuser");
        entry.setDirectory("/test/path");
        entry.setExtension(".txt");

        historyRepository.save(entry);

        List<HistoryEntry> entries = historyRepository.findAll();
        assertThat(entries).hasSize(1);
        assertEquals(entries.get(0).getTimestamp(), now);
        assertEquals(entries.get(0).getUsername(), "testuser");
        assertEquals(entries.get(0).getDirectory(), "/test/path");
        assertEquals(entries.get(0).getExtension(), ".txt");
    }
}