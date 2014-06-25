package com.ehsunbehravesh.programmingassignment.entity;

/**
 *
 * @author Ehsun Behravesh
 */
public class Message {

    public static final String KIND = "Message";
    
    private Long id;
    private Assignment assignment;
    private Long timestamp;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
