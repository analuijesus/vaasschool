package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public class Question extends Activity{

    private String statiment;
    private QuestionType type = QuestionType.SINGLE_ANSWER;

    public Question(String title, String code, Section section, String statiment) {
        super(title, code, section);

        Validator.nullValidation(section);
        Validator.writtenFieldValidation(title);

        this.statiment = statiment;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getStatiment() {
        return statiment;
    }

    @Override
    public int compareTo(Activity anotherQuestion) {
        return this.getOrder().compareTo(anotherQuestion.getOrder());
    }
}
