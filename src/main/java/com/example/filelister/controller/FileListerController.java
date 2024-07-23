package com.example.filelister.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.filelister.dto.HistoryDTO;
import com.example.filelister.service.HistoryService;
import com.example.filelister.service.UniqueFileListerService;

/**
 * Spring REST controller that lists unique files in a specified directory with a
 * given extension and saves the history of the operation.
 */
@RestController
public class FileListerController {

    @Autowired
    private UniqueFileListerService uniqueFileLister;

    @Autowired 
    private HistoryService historyService;

    /**
     * Retrieves unique file names and their occurrences in a specified directory and extension.
     * 
     * @param dir The directory path from which you want to retrieve unique files. If
     * no value is provided, it defaults to "/tmp".
     * @param ext The extension of the files you want to retrieve from the specified
     * directory. If no value is provided, it defaults to "txt".
     * @return A Map containing file names and their occurrences in the specified
     * directory with the specified extension.
     */
    @GetMapping("/get_unique_files")
    public Map<String, Integer> getUniqueFiles(@RequestParam(value = "dir", defaultValue = "/tmp") String dir,
    @RequestParam(value = "ext", defaultValue = "txt") String ext) {
        historyService.addHistory(new HistoryDTO(System.currentTimeMillis(), System.getProperty("user.name"), dir, ext));
        return uniqueFileLister.getFileNamesAndOccurrences(dir, ext);
    }
}
