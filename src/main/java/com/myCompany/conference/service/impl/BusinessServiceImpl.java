package com.myCompany.conference.service.impl;

import com.myCompany.conference.dao.factory.DAOFactory;
import com.myCompany.conference.dao.factory.impl.MySqlDAOFactory;
import com.myCompany.conference.dao.impl.AbstractDAO;
import com.myCompany.conference.dao.impl.ConferenceDAO;
import com.myCompany.conference.dao.impl.UserDAO;
import com.myCompany.conference.entity.Conference;
import com.myCompany.conference.entity.Role;
import com.myCompany.conference.entity.Speaker;
import com.myCompany.conference.entity.User;
import com.myCompany.conference.exception.ApplicationException;
import com.myCompany.conference.model.Items;
import com.myCompany.conference.service.BusinessService;

import java.sql.Connection;
import java.util.List;

class BusinessServiceImpl implements BusinessService {
        private EncryptionServiceImpl encryptionService = EncryptionServiceImpl.instance;
        private final Connection connection;
        private DAOFactory parentFactory = MySqlDAOFactory.getInstance();
        private AbstractDAO<Conference> conference;
        private AbstractDAO<User> user;

    public BusinessServiceImpl(ServiceManager serviceManager) {
        this.connection = serviceManager.connection;
        conference = parentFactory.createConference(connection);
        user = parentFactory.createUser(connection);
    }

    @Override
    public Items<Conference> listConference(int offset, int limit) {
        Items<Conference> items = new Items<>();
        items.setItems(((ConferenceDAO)conference).findWithLimit(offset, limit));
        items.setCount(((ConferenceDAO)conference).countConference());
        return items;
    }

    @Override
    public User signIn(String login, String password) {
        if(user.findByString("email", login).size() >= 0) {
            User userWithDB = user.findByString("email", login).get(0);
            int salt = ((UserDAO) user).findSalt(userWithDB.getId());
            String passwordWithSalt = salt + password;

            if (encryptionService.checkPassword(passwordWithSalt, userWithDB.getPassword())) {
                return userWithDB;
            }
        }
        throw new ApplicationException("User not registered in DB");
    }

    @Override
    public User registered(String name, String surname, String email, String password) {
        int salt = encryptionService.generationSalt();
        String passwordWithSalt = salt + password;
        String protectPassword = encryptionService.encryption(passwordWithSalt);
        long userId = user.findAll().size() + 1;
        User newUser = new User(userId, email, protectPassword, name, surname, Role.User);
        user.create(newUser);
        ((UserDAO) user).updateSalt(salt, userId);
        return newUser;
    }

    @Override
    public Speaker getSpeakerDate(User user) {
        long rating = ((UserDAO)this.user).findByRating(user.getId());
        long bonus = ((UserDAO)this.user).findByBonus(user.getId());
        Speaker speaker = new Speaker(user, rating, bonus);
        return speaker;
    }
}
