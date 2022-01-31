package br.com.vaasschool.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Section implements Comparable<Section>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da seção precisa ser preenchida")
    private String name;

    @NotBlank(message = "Insira um código válido.O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;
    private Boolean active = false;
    private Boolean test = false;

    @Column(name = "order_visualization")
    private Integer order;

    @NotNull(message = "A seção deve ter um curso associado.")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public Section(String name, String code, Course course) {
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
