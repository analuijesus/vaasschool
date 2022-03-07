package br.com.vaasschool.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserForm {

    private String email;
    private String password;

    public UserForm(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
