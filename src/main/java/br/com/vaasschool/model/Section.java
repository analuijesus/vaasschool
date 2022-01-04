package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import javax.persistence.*;

@Entity
public class Section implements Comparable<Section>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Boolean active = false;
    private Boolean test = false;

    @Column(name = "order_visualization")
    private Integer order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public Section(String name, String code, Course course) {
        Validator.notNull(course);
        Validator.notNullOrEmpty(name);
        Validator.isCode(code);

        this.name = name;
        this.code = code;
        this.course = course;
    }

    @Deprecated
    public Section() {
    }

    public String getCode() {
        return code;
    }

    @Override
    public int compareTo(Section anotherSection) {
        return this.order.compareTo(anotherSection.order);
    }
}
