package br.com.vaasschool.model;

public class Course {

    private String name;
    private String code;
    private Integer timeCouse;
    private Boolean visibility = false;
    private String description;
    private String instructorName;
    private String menu;
    private String learnedSkill;

    public Course(String name, String code, Integer timeCouse, String instructorName) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        boolean validateCode = code.matches("([a-z^-]+)");
        if (!validateCode) {
            throw new IllegalArgumentException("Código inválido");
        }

        if (timeCouse < 1 || timeCouse > 20){
            throw new IllegalArgumentException("Carga horária inválida");
        }

        if (instructorName == null || instructorName.trim().isEmpty()){
            throw new IllegalArgumentException("Nome do instrutor inválido");
        }

        this.name = name;
        this.code = code;
        this.timeCouse = timeCouse;
        this.instructorName = instructorName;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setLearnedSkill(String learnedSkill) {
        this.learnedSkill = learnedSkill;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getTimeCouse() {
        return timeCouse;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getMenu() {
        return menu;
    }

    public String getLearnedSkill() {
        return learnedSkill;
    }
}
