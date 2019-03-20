package com.myCompany.conference.dao.factory;

import com.myCompany.conference.dao.factory.impl.MySqlDAOFactory;
import com.myCompany.conference.dao.impl.ConferenceDAO;
import com.myCompany.conference.dao.impl.ReviewDAO;
import com.myCompany.conference.dao.impl.SpeakerDAO;
import com.myCompany.conference.dao.impl.UserDAO;

import java.sql.Connection;

public abstract class DAOFactory {
    private static volatile DAOFactory daoFactory;

    public abstract ReviewDAO createReview(Connection connection);
    public abstract SpeakerDAO createSpeaker(Connection connection);
    public abstract UserDAO createUser(Connection connection);
    public abstract ConferenceDAO createConference(Connection connection);

    public static DAOFactory getInstance(){
        if(isNullDaoFactory(daoFactory)){
            synchronized (DAOFactory.class){
                if(isNullDaoFactory(daoFactory)){
                    daoFactory = new MySqlDAOFactory();
                }
            }
        }
        return daoFactory;
    }
    private static boolean isNullDaoFactory(DAOFactory daoFactory){
        return daoFactory == null;
    }
}
