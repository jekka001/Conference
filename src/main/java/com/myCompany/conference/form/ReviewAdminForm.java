package com.myCompany.conference.form;

import com.myCompany.conference.entity.Speaker;
import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.service.I18nService;

public class ReviewAdminForm extends AbstractForm{
    private Long idReview;
    private String topic;
    private String speakerName;
    private Integer register;
    private Integer visitors;


    public Long getIdReview() {
        return idReview;
    }
    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getSpeakerName() {
        return speakerName;
    }
    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }
    public Integer getRegister() {
        return register;
    }
    public void setRegister(Integer register) {
        this.register = register;
    }
    public Integer getVisitors() {
        return visitors;
    }
    public void setVisitors(Integer visitors) {
        this.visitors = visitors;
    }

    @Override
    public void validate(I18nService i18nService) throws ValidateException {
        if(!verification.fieldLong(idReview.toString())){
            throw new ValidateException("idReview is required");
        }
        if(topic == null){
            throw new ValidateException("topic is required");
        }
        if(!verification.fieldString(speakerName)){
            throw new ValidateException("speakerName is required");
        }
        if(!verification.fieldLong(register.toString())){
            throw new ValidateException("register is required");
        }
        if(!verification.fieldLong(visitors.toString())){
            throw new ValidateException("visitors is required");
        }
    }
}
