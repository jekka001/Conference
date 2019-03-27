package com.myCompany.conference.form;

import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.service.I18nService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class ContactForm extends AbstractForm{
    private String name;
    private String email;
    private String phone;
    private String message;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void validate(I18nService i18nService) throws ValidateException {
        if(!verification.fieldString(name)){
            throw new ValidateException("name is required");
        }
        if(!verification.fieldEmail(email)){
            throw new ValidateException("email is required");
        }
        if(!verification.fieldPhone(phone)){
            throw new ValidateException("phone is required");
        }
        if(!verification.fieldString(message)){
            throw new ValidateException("message is required");
        }
    }
}
