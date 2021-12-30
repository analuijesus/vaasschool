package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Question")
public class Question extends Activity{

    private String statiment;

    @Column(name = "question_type", columnDefinition = "ENUM")
    @Enumerated(EnumType.STRING)
    private QuestionType type = QuestionType.SINGLE_ANSWER;

    public Question(String title, String code, Section section, String statiment) {
        super(title, code, section);

        Validator.notNull(section);
        Validator.notNullOrEmpty(title);

        this.statiment = statiment;
    }

    @Deprecated
    public Question() {

    }
}
