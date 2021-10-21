package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public class Video extends Activity{

    private String url;
    private Long minutes;
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);

        Validator.notNull(section);
        Validator.notNullOrEmpty(url);

        this.url = url;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getUrl() {
        return url;
    }
}
