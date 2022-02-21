package br.com.vaasschool.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Alternative implements Comparable<Alternative> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Um texto explicativo da alternativa deve ser preenchido.")
    private String text;
    @Column(name = "order_visualization")
    private Integer order;
    private Boolean correct;
    private String justification;
    @NotNull(message = "A alternativa deve ter uma questão associada.")
    @ManyToOne (fetch = FetchType.LAZY)
    private Question question;

    @Deprecated
    public Alternative() {
    }

    public Alternative(String text, Boolean correct, Question question) {
        this.text = text;
        this.correct = correct;
        this.question = question;
    }

    @Override
    public int compareTo(Alternative anotherAlternative) {
        return this.order.compareTo(anotherAlternative.order);
    }
}
