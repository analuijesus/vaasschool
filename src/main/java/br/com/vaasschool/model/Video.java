package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Video")
public class Video extends Activity{

    private String url;
    private int minutes;
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        Validator.notNull(section);
        Validator.notNullOrEmpty(url);

        this.url = url;
    }

    @Deprecated
    public Video() {
    }
}