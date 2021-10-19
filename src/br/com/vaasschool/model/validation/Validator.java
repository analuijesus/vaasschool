package br.com.vaasschool.model.validation;

import com.sun.jdi.ObjectReference;

public class Validator {

    public static void codeValidation(String code) {
        if (code == null) throw new NullPointerException("Código não pode ser nulo");

        boolean validateCode = code.matches("([a-z0-9^-]+)");
        if (!validateCode) throw new IllegalArgumentException("Código inválido");
    }

    public static void writtenFieldValidation(String validationString) {
        if (validationString == null || validationString.trim().isEmpty()) throw new IllegalArgumentException("String inválida");
    }

    public static void nullValidation(Object object){
        if (object == null) throw new NullPointerException("Valor não pode ser nulo");
    }
}
