package br.com.vaasschool.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("Question")
public class Question extends Activity{

    @NotBlank(message = "O enunciado deve ser preeenchido.")
    private String statement;
    @Column(name = "question_type", columnDefinition = "ENUM")
    @Enumerated(EnumType.STRING)
    private QuestionType type = QuestionType.SINGLE_ANSWER;

    @Deprecated
    public Question() {
    }

    public Question(String title, String code, Section section, String statement) {
        super(title, code, section);
        this.statement = statement;
    }
}