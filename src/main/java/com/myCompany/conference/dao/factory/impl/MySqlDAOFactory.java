package com.myCompany.conference.dao.factory.impl;

import com.myCompany.conference.dao.factory.DAOFactory;
import com.myCompany.conference.dao.impl.ConferenceDAO;
import com.myCompany.conference.dao.impl.ReviewDAO;
import com.myCompany.conference.dao.impl.SpeakerDAO;
import com.myCompany.conference.dao.impl.UserDAO;
import com.myCompany.conference.entity.Conference;

import java.sql.Connection;

public class MySqlDAOFactory extends DAOFactory {
    @Override
    public ReviewDAO createReview(Connection connection) {
        return new ReviewDAO(connection);
    }

    @Override
    public SpeakerDAO createSpeaker(Connection connection) {
        return new SpeakerDAO(connection);
    }

    @Override
    public UserDAO createUser(Connection connection) {
        return new UserDAO(connection);
    }

    @Override
    public ConferenceDAO createConference(Connection connection) {
        return new ConferenceDAO(connection);
    }
}
