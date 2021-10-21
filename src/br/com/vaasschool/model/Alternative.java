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

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getText() {
        return text;
    }

    public Integer getOrder() {
        return order;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public String getJustification() {
        return justification;
    }

    public Question getQuestion() {
        return question;
    }

    @Override
    public int compareTo(Alternative anotherAlternative) {
        return this.order.compareTo(anotherAlternative.order);
    }
}
