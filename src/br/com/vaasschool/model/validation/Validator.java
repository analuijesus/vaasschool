package br.com.vaasschool.model.validation;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Validator {

    public static void isCode(String code) {
       isCode(code, "Código inválido");
    }

    public static void isCode(String code, String errorMessage) {
        notNull(code);

        boolean validateCode = code.matches("([a-z0-9^-]+)");
        if (!validateCode) throw new IllegalArgumentException(errorMessage);
    }

    public static void notNullOrEmpty(String validationString) {
       notNullOrEmpty(validationString,"String vazia");
    }

    public static void notNullOrEmpty(String validationString,String errorMessage) {
        notNull(validationString);
        if(validationString.trim().isEmpty())
            throw new IllegalArgumentException(errorMessage);
    }

    public static void notNull(Object object) {
       notNull(object,"Valor nulo");
    }

    public static void notNull(Object object, String errorMessage) {
        if (object == null)
            throw new IllegalArgumentException(errorMessage);
    }

    public static void validInterval(int value, int minValue, int maxValue) {
        validInterval(value,minValue,maxValue,"Intervalo inválido");
    }

    public static void validInterval(int value, int minValue, int maxValue, String errorMessage){
        if (value < minValue || value > maxValue){
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
