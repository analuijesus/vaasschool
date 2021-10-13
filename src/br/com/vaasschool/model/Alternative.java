package br.com.vaasschool.model;

public class Alternative implements Comparable<Alternative>{

    private String text;
    private Integer order;
    private Boolean correction;
    private String justification;
    private Question question;

    public Alternative(String text, Boolean correction, Question question) {

        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Texto inválido");
        }

        this.text = text;
        this.correction = correction;
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

    public Boolean getCorrection() {
        return correction;
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
