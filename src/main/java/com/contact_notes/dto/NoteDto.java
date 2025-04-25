package com.contact_notes.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class NoteDto {
    private Long id;
    private String body;

    // temporary holder for any incoming fields
    private Map<String, Object> otherFields = new HashMap<>();

    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        otherFields.put(key, value);
        // normalize known aliases
        if ("note_body".equals(key) || "note_text".equals(key)) {
            this.body = value.toString();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}
