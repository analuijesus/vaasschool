package br.com.vaasschool.model;

import java.util.Arrays;

public enum CourseVisibility {

    PRIVATE ("PRIVADA"),
    PUBLIC ("PÚBLICA");

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    CourseVisibility(String displayName) {
        this.displayName = displayName;
    }

    public static CourseVisibility languageType(String value){
        return Arrays.stream(CourseVisibility.values())
                .filter(cv -> cv.getDisplayName().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Visibilidade não encontrada"));
    }
}