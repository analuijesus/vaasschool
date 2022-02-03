package br.com.vaasschool.model;

public enum CourseVisibility {
    PRIVATE("Privado"),
    PUBLIC("Público");

    private String description;

    CourseVisibility(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static CourseVisibility fromDisplayName(String value) {
        if (value.equalsIgnoreCase("PÚBLICA")) {
            return PUBLIC;
        }
        return PRIVATE;
    }
}