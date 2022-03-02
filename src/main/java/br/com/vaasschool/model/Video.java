package br.com.vaasschool.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor(onConstructor = @__(@Deprecated))
@Entity
@DiscriminatorValue("Video")
public class Video extends Activity{

    @NotBlank(message = "A url deve ser preeenchida.")
    private String url;
    private int minutes;
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        this.url = url;
    }
}