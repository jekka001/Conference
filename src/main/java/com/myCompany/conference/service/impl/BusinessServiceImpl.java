package com.myCompany.conference.service.impl;

import com.myCompany.conference.Constants;
import com.myCompany.conference.dao.factory.DAOFactory;
import com.myCompany.conference.dao.factory.impl.MySqlDAOFactory;
import com.myCompany.conference.dao.impl.AbstractDAO;
import com.myCompany.conference.dao.impl.ConferenceDAO;
import com.myCompany.conference.dao.impl.ReviewDAO;
import com.myCompany.conference.dao.impl.UserDAO;
import com.myCompany.conference.entity.*;
import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.form.*;
import com.myCompany.conference.model.Items;
import com.myCompany.conference.service.BusinessService;
import com.myCompany.conference.service.EncryptionService;
import com.myCompany.conference.service.I18nService;
import com.myCompany.conference.service.NotificationService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

class BusinessServiceImpl implements BusinessService {
        private static final Logger LOGGER = Logger.getLogger(BusinessServiceImpl.class);
        private final EncryptionService encryptionService;
        private final Connection connection;
        private final DAOFactory parentFactory;
        private final AbstractDAO<User> user;
        private final AbstractDAO<Review> review;
        private final NotificationService notificationService;
        private final I18nService i18nService;
        private final AbstractDAO<Conference> conference;

    public BusinessServiceImpl(ServiceManager serviceManager) {
        super();
        this.connection = serviceManager.connection;
        parentFactory = MySqlDAOFactory.getInstance();
        notificationService = serviceManager.notificationService;
        encryptionService = serviceManager.encryptionService;
        i18nService = serviceManager.i18nService;
        user = parentFactory.createUser(connection);
        review = parentFactory.createReview(connection);
        conference = parentFactory.createConference(connection);
    }

    @Override
    public Items<Conference> listConference(int offset, int limit) {
        Items<Conference> items = new Items<>();
        items.setItems(((ConferenceDAO)conference).findWithLimit(offset, limit));
        items.setCount(((ConferenceDAO)conference).countConference());
        return items;
    }

    @Override
    public Items<Review> listReview(long idConference) {
        Items<Review> items = new Items<>();
        items.setItems(review.findByLong("id_conference", idConference));
        return items;
    }

    @Override
    public User signIn(SignInForm form) throws ValidateException {
        form.validate(i18nService);
        if(user.findByString("email", form.getLogin()).size() > 0) {
            User userWithDB = user.findByString("email", form.getLogin()).get(0);
            int salt = ((UserDAO) user).findSalt(userWithDB.getId());
            String passwordWithSalt = salt + form.getPassword();

            if (encryptionService.checkPassword(passwordWithSalt, userWithDB.getPassword())) {
                return userWithDB;
            }
        }
        return null;
    }

    @Override
    public User createUser(RegisteredForm form) throws ValidateException {
        form.validate(i18nService);
        int salt = encryptionService.generationSalt();
        String passwordWithSalt = salt + form.getPassword();
        String protectPassword = encryptionService.encryption(passwordWithSalt);
        long userId = user.findAll().size() + 1;
        User newUser = new User(userId, form.getEmail(), protectPassword, form.getName(), form.getSurname(), Role.User);
        user.create(newUser);
        ((UserDAO) user).updateSalt(salt, userId);
        return newUser;
    }

    @Override
    public void createContactRequest(ContactForm form) throws ValidateException {
        form.validate(i18nService);
        String title = form.getName() + "( " + form.getPhone() + " " + form.getEmail() +  " )";
        String content = form.getMessage();
        notificationService.sendNotification(title, content, Constants.EMAIL_SITE, Constants.EMAIL_SITE);
    }

    @Override
    public Speaker getSpeakerDate(User user) {
        long rating = ((UserDAO)this.user).findByRating(user.getId());
        long bonus = ((UserDAO)this.user).findByBonus(user.getId());
        return new Speaker(user, rating, bonus);
    }

    @Override
    public void uploadRegisterReview(long idReview, long idUser) {
        Review tempReview = review.findById(idReview);
        tempReview.getRegistered().add(user.findById(idUser));
        review.update(tempReview);
    }

    @Override
    public void uploadConference(ConferenceForm form) throws ValidateException {
        form.validate(i18nService);
        System.out.println(form.getIdConference());
        System.out.println(form.getTimeConduction());
        System.out.println(form.getVenue());
        Conference tempConference = conference.findById(form.getIdConference());
        tempConference.setTimeConduction(new Timestamp(form.getTimeConduction().getTime()));
        tempConference.setVenue(form.getVenue());
        conference.update(tempConference);
    }

    @Override
    public void uploadReview(ReviewAdminForm form) throws ValidateException {
        form.validate(i18nService);
        Review newReview = review.findById(form.getIdReview());
        newReview.setVisitors(form.getVisitors());
        review.update(newReview);
    }

    @Override
    public void createReview(ReviewForm form, long idConference) {
        long idReview = review.findAll().size() + 1;
        Review tempReview = new Review(idReview, form.getTopic(),new ArrayList<>(), 0, form.getSpeakerName(), StateReview.Consideration);
        review.create(tempReview);
        ((ReviewDAO)review).updateConferenceId(tempReview, idConference);
    }


}
