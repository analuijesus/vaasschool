package br.com.vaasschool.controller.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor(onConstructor = @__(@Deprecated))
@Entity
@DiscriminatorValue("Question")
public class Question extends Activity{

    @NotBlank(message = "O enunciado deve ser preeenchido.")
    private String statement;
    @Column(name = "question_type", columnDefinition = "ENUM")
    @Enumerated(EnumType.STRING)
    private QuestionType type = QuestionType.SINGLE_ANSWER;

    public Question(String title, String code, Section section, String statement) {
        super(title, code, section);
        this.statement = statement;
    }
}