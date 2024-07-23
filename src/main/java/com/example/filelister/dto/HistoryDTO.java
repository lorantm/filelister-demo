package com.example.filelister.dto;

/**
 * DTO that represents an entry in the file query history database.
 */
public class HistoryDTO { 
    private long timestamp;
    private String username;
    private String directory;
    private String extension;

    public HistoryDTO() {}

    public HistoryDTO(long timestamp, String username, String directory, String extension) {
        this.timestamp = timestamp;
        this.username = username;
        this.directory = directory;
        this.extension = extension;
    }

    /**
     * Returns the unix timestamp of the query.
     * 
     * @return The unix timestamp of the query.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the name of the user who performed the query.
     * 
     * @return The name of the user who performed the query.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the queried directory.
     * 
     * @return The queried directory.
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Returns the queried file extension.
     * 
     * @return The queried file extension.
     */
    public String getExtension() {
        return extension;
    }
}
