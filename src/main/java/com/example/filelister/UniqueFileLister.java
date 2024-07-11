package com.example.filelister;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Implements core file listing functionality.
 */
@Component
public class UniqueFileLister {

    private static final Logger LOG = LoggerFactory.getLogger(UniqueFileLister.class);

    /**
     * Lists files in a directory with a specific extension and returns a map of file names and their occurrences.
     * 
     * @param dirPath The directory in which you want to search for files.
     * @param extension The extension of the files you want to search for in the given directory.
     * @return A Map containing file names and their occurrences in the specified directory with the given file extension.
     */
    public Map<String, Integer> getFileNamesAndOccurrences(String dirPath, String extension) {
        LOG.info("Listing files in directory '{}' with extension '{}'", dirPath, extension);
        Map<String, Integer> fileNamesAndOccurrences = new TreeMap<>();
        return listFiles(new File(dirPath), extension, fileNamesAndOccurrences);
    }

    private Map<String, Integer> listFiles(File dir, String extension, Map<String, Integer> fileNamesAndOccurrences) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    listFiles(f, extension, fileNamesAndOccurrences);
                } else if (extension.equals("*") || f.getName().endsWith(extension)) {
                    int occurrences = fileNamesAndOccurrences.containsKey(f.getName()) ?
                        fileNamesAndOccurrences.get(f.getName()) + 1 : 1;
                    fileNamesAndOccurrences.put(f.getName(), occurrences);
                }
            }
        } else {
            LOG.warn("Listing files in directory '{}' returned null array", dir.getAbsolutePath());
        }
        return fileNamesAndOccurrences;
    }
}
