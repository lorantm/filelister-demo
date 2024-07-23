package com.example.filelister.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity that represents an entry in the file query history database.
 */
@Entity
@Table(name = "history")
public class HistoryEntity {

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
     * Default constructor.
     */
    public HistoryEntity() {}

    /**
     * Parameterized constructor.
     * @param timestamp Unix timestamp of the query.
     * @param username Name of the user who performed the query.
     * @param directory Directory from which the files were queried.
     * @param extension Extension of the queried files.
     */
    public HistoryEntity(long timestamp, String username, String directory, String extension) {
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
