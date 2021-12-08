package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public class Alternative implements Comparable<Alternative>{

    private String text;
    private Integer order;
    private Boolean correct;
    private String justification;
    private Question question;

    public Alternative(String text, Boolean correct, Question question) {
        Validator.notNull(question);
        Validator.notNullOrEmpty(text);

        this.text = text;
        this.correct = correct;
        this.question = question;
    }

    @Override
    public int compareTo(Alternative anotherAlternative) {
        return this.order.compareTo(anotherAlternative.order);
    }
}
