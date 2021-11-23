package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public class Question extends Activity{

    private String statiment;
    private QuestionType type = QuestionType.SINGLE_ANSWER;

    public Question(String title, String code, Section section, String statiment) {
        super(title, code, section);

        Validator.notNull(section);
        Validator.notNullOrEmpty(title);

        this.statiment = statiment;
    }
}
