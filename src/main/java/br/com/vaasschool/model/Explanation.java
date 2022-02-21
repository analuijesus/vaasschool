package br.com.vaasschool.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("Explanation")
public class Explanation extends Activity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Um texto explicativo da atividade deve ser preenchido.")
    private String text;

    @Deprecated
    public Explanation() {
    }

    public Explanation(String title, String code, Section section, String text) {
        super(title, code, section);
        this.text = text;
    }
}