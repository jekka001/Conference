package com.myCompany.conference.form;

import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.service.I18nService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class RegisteredForm extends AbstractForm{
    private String name;
    private String surname;
    private String email;
    private String password;
    private String repeatPassword;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRepeatPassword() {
        return repeatPassword;
    }
    public void setRepeatPassword(String repeat_password) {
        this.repeatPassword = repeat_password;
    }

    @Override
    public void validate(I18nService i18nService) throws ValidateException {
        if(!verification.fieldString(name)){
            throw new ValidateException("name is required");
        }
        if(!verification.fieldString(surname)){
            throw new ValidateException("surname is required");
        }
        if(!verification.fieldEmail(email)){
            throw new ValidateException("email is required");
        }
        if(!verification.fieldString(password)){
            throw new ValidateException("password is required");
        }
        if(!verification.fieldString(repeatPassword)){
            throw new ValidateException("repeatPassword is required");
        }
    }
}
