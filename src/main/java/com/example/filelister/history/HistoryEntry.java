package com.example.filelister.history;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Data class that represents an entry in the file query history database.
 */
@Entity
@Table(name = "history")
public class HistoryEntry {

    @Id
    @GeneratedValue
    private long id;
    
    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "directory", nullable = false)
    private String directory;

    @Column(name = "extension", nullable = false)
    private String extension;

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

    /**
     * Sets the unix the timestamp of the query.
     * 
     * @param timestamp The unix timestamp of the query.
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Sets the name of the user who performed the query.
     * 
     * @param username The name of the user who performed the query.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the queried directory.
     * 
     * @param directory The queried directory.
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * Sets the queried file extension.
     * 
     * @param extension The queried file extension.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }
}
