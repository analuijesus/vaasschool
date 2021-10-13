package br.com.vaasschool.model;

public class Question extends Activity{

    private String utterance;
    private QuestionType type = QuestionType.SINGLE_ANSWER;

    public Question(String title, String code, Section section, String utterance) {
        super(title, code, section);

        if (utterance == null || utterance.trim().isEmpty()) {
            throw new IllegalArgumentException("Enunciado inválido");
        }

        this.utterance = utterance;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getUtterance() {
        return utterance;
    }
}
