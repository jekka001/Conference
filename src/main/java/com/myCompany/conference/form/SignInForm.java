package com.myCompany.conference.form;

import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.service.I18nService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class SignInForm extends AbstractForm{
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void validate(I18nService i18nService) throws ValidateException {
        if(!verification.fieldEmail(login)){
            System.out.println(login);
            throw new ValidateException("login is required");
        }
        if(!verification.fieldString(password)){
            System.out.println(password);
            throw new ValidateException("password is required");
        }
    }
}
