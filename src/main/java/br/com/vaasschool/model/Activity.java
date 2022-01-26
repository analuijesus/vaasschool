package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import javax.persistence.*;

import static org.hibernate.type.EnumType.ENUM;


@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", columnDefinition = "ENUM('EXPLANATION', 'VIDEO','QUESTION')")
@Entity
public abstract class Activity implements Comparable<Activity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;

    @Column(name = "order_visualization")
    private Integer order;
    private Boolean active = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    public Activity(String title, String code, Section section) {
        Validator.notNull(section);
        Validator.notNullOrEmpty(title);
        Validator.isCode(code);

        this.title = title;
        this.code = code;
        this.section = section;
    }

    @Deprecated
    public Activity() {
    }

    public String getCode() {
        return code;
    }

    @Override
    public int compareTo(Activity anotherActivity) {
        return this.order.compareTo(anotherActivity.order);
    }
}
