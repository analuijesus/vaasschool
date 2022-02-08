package br.com.vaasschool.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", columnDefinition = "ENUM('EXPLANATION', 'VIDEO','QUESTION')")
@Entity
public abstract class Activity implements Comparable<Activity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título deve ser preenchido.")
    private String title;

    @NotBlank(message = "O código da atividade é obrigatório.")
    @Pattern(regexp = "([a-z0-9^-]+)", message = "Insira um código válido. O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;

    @Column(name = "order_visualization")
    private Integer order;
    private Boolean active = false;

    @NotNull(message = "A atividade deve ter uma seção associada.")
    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @Deprecated
    public Activity() {
    }

    public Activity(String title, String code, Section section) {
        this.title = title;
        this.code = code;
        this.section = section;
    }

    public String getCode() {
        return code;
    }

    @Override
    public int compareTo(Activity anotherActivity) {
        return this.order.compareTo(anotherActivity.order);
    }
}
