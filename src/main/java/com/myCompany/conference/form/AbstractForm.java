package com.myCompany.conference.form;

import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.service.I18nService;
import com.myCompany.conference.util.Verification;
import com.myCompany.conference.util.VerificationImpl;

import java.util.Locale;

public class AbstractForm {
    protected Locale locale;
    protected Verification verification = new VerificationImpl();
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    public Locale getLocale(){
        return locale;
    }

    public void validate(I18nService i18nService) throws ValidateException {

    }
}
