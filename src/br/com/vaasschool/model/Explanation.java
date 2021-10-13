package br.com.vaasschool.model;

public class Explanation extends Activity{

    private String text;

    public Explanation(String title, String code, Section section, String text) {
        super(title, code, section);

        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Texto inválido");
        }

        this.text = text;
    }

    public String getText() {
        return text;
    }
}
