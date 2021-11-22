package br.com.vaasschool.model;

public enum CourseVisibility {

    PRIVATE,
    PUBLIC;

    public static CourseVisibility languageType(String value) {
        if (value.equalsIgnoreCase("PÚBLICA")) {
            return PUBLIC;
        }
        return PRIVATE;
    }
}