package br.com.vaasschool.model;

public class Activity implements Comparable<Activity> {

    private String title;
    private String code;
    private Integer order;
    private Boolean activeActivity = false;
    private Object type;
    private Section section;

    public Activity(String title, String code, Section section) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido");
        }

        boolean validateCode = code.matches("([a-z^-]+)");
        if (!validateCode) {
            throw new IllegalArgumentException("Código inválido");
        }

        this.title = title;
        this.code = code;
        this.section = section;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getActiveActivity() {
        return activeActivity;
    }

    public void setActiveActivity(Boolean activeActivity) {
        this.activeActivity = activeActivity;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public Section getSection() {
        return section;
    }

    @Override
    public int compareTo(Activity anotherActivity) {
        return this.order.compareTo(anotherActivity.order);
    }
}
