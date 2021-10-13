package br.com.vaasschool.model;

public class Section implements Comparable<Section>{

    private String name;
    private String code;
    private Boolean activeSection = false;
    private Boolean test = false;
    private Integer order;
    private Course course;

    public Section(String name, String code, Course course) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        boolean validateCode = code.matches("([a-z^-]+)");
        if (!validateCode) {
            throw new IllegalArgumentException("Código inválido");
        }

        this.name = name;
        this.code = code;
        this.course = course;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Boolean getActive() {
        return activeSection;
    }

    public Boolean getTest() {
        return test;
    }

    public Course getCourse() {
        return course;
    }

    public void setActive(Boolean active) {
        this.activeSection = active;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }

    @Override
    public int compareTo(Section anotherSection) {
        return this.order.compareTo(anotherSection.order);
    }
}
