package com.myCompany.conference.form;

import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.service.I18nService;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;

public class ConferenceForm extends AbstractForm {
    private Long idConference;
    private Timestamp timeConduction;
    private String venue;

    public Long getIdConference() {
        return idConference;
    }
    public void setIdConference(Long idConference) {
        this.idConference = idConference;
    }
    public Timestamp getTimeConduction() {
        return timeConduction;
    }
    public void setTimeConduction(Timestamp timeConduction) {
        this.timeConduction = timeConduction;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Override
    public void validate(I18nService i18nService) throws ValidateException {
        if(!verification.fieldLong(idConference.toString())){
            throw new ValidateException("idConference is required");
        }
        if(timeConduction == null){
            throw new ValidateException("timeConduction is required");
        }
        if(!verification.fieldString(venue)){
            throw new ValidateException("venue is required");
        }
    }
}
