package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public class Video extends Activity{

    private String url;
    private Long minutes;
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);

        Validator.nullValidation(section);
        Validator.writtenFieldValidation(url);

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

    @Override
    public int compareTo(Activity anotherVideo) {
        return this.getOrder().compareTo(anotherVideo.getOrder());
    }
}
