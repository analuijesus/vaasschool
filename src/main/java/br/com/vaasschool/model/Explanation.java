package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Explanation")
public class Explanation extends Activity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    public Explanation(String title, String code, Section section, String text) {
        super(title, code, section);

        Validator.notNull(section);
        Validator.notNullOrEmpty(text);

        this.text = text;
    }

    @Deprecated
    public Explanation() {
    }
}
