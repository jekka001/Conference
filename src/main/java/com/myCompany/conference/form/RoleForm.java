package com.myCompany.conference.form;

import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.service.I18nService;
import org.apache.commons.lang.StringUtils;

public class RoleForm extends AbstractForm{
    private String role;

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void validate(I18nService i18nService) throws ValidateException {
        if(!verification.fieldRole(role)){
            throw new ValidateException("role is required");
        }
    }
}
