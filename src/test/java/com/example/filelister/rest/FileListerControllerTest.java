package com.example.filelister.rest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.filelister.UniqueFileLister;
import com.example.filelister.history.HistoryRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class FileListerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UniqueFileLister uniqueFileLister;

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private FileListerController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUniqueFiles() throws Exception {
        when(uniqueFileLister.getFileNamesAndOccurrences(anyString(), anyString()))
                .thenReturn(Map.of("file1.txt", 1, "file2.txt", 2));

        mockMvc.perform(MockMvcRequestBuilders.get("/get_unique_files?dir=/tmp&ext=.txt"))
                .andExpect(status().isOk());
    }

	// TODO write more tests
}
