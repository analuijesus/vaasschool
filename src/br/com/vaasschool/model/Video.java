package br.com.vaasschool.model;

public class Video extends Activity{

    private String url;
    private Long minutes;
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);

        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL inválida");
        }

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
