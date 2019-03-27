package com.myCompany.conference.service;

import com.myCompany.conference.entity.*;
import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.form.*;
import com.myCompany.conference.model.Items;

public interface BusinessService {

    Items<Conference> listConference(int offset, int limit);
    Items<Review> listReview(long idConference);
    Speaker getSpeakerDate(User user);
    void createContactRequest(ContactForm form) throws ValidateException;
    /**
     * @return null if entity not found
     */
    User signIn(SignInForm form) throws ValidateException;
    User createUser(RegisteredForm form) throws ValidateException;

    void uploadRegisterReview(long idReview, long idUser);
    void uploadConference(ConferenceForm form) throws ValidateException;
    void uploadReview(ReviewAdminForm form) throws ValidateException;

    void createReview(ReviewForm form, long idConference);

}
