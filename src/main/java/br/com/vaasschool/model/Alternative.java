package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import javax.persistence.*;

@Entity
public class Alternative implements Comparable<Alternative> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @Column(name = "order_visualization")
    private Integer order;
    private Boolean correct;
    private String justification;

    @ManyToOne (fetch = FetchType.LAZY)
    private Question question;

    public Alternative(String text, Boolean correct, Question question) {
        Validator.notNull(question);
        Validator.notNullOrEmpty(text);

        this.text = text;
        this.correct = correct;
        this.question = question;
    }

    @Deprecated
    public Alternative() {

    }

    @Override
    public int compareTo(Alternative anotherAlternative) {
        return this.order.compareTo(anotherAlternative.order);
    }
}
