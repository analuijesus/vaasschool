package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public class Explanation extends Activity{

    private String text;

    public Explanation(String title, String code, Section section, String text) {
        super(title, code, section);

        Validator.notNull(section);
        Validator.notNullOrEmpty(text);

        this.text = text;
    }

    public String getText() {
        return text;
    }
}
