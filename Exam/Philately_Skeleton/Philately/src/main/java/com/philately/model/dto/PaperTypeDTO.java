package com.philately.model.dto;

public class PaperTypeDTO {
    private String value;
    private String displayName;

    // Constructor for initialization
    public PaperTypeDTO(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    // Getters and Setters
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
