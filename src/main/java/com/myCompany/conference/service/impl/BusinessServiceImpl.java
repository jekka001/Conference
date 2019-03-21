package com.myCompany.conference.service.impl;

import com.myCompany.conference.dao.factory.DAOFactory;
import com.myCompany.conference.dao.factory.impl.MySqlDAOFactory;
import com.myCompany.conference.dao.impl.AbstractDAO;
import com.myCompany.conference.dao.impl.ConferenceDAO;
import com.myCompany.conference.entity.Conference;
import com.myCompany.conference.model.Items;
import com.myCompany.conference.service.BusinessService;

import java.sql.Connection;
import java.util.List;

class BusinessServiceImpl implements BusinessService {
        private final Connection connection;
        private DAOFactory parentFactory = MySqlDAOFactory.getInstance();
        private AbstractDAO<Conference> conference;

    public BusinessServiceImpl(ServiceManager serviceManager) {
        this.connection = serviceManager.connection;
        conference = parentFactory.createConference(connection);
    }

    @Override
    public Items<Conference> listConference(int offset, int limit) {
        Items<Conference> items = new Items<>();
        items.setItems(((ConferenceDAO)conference).findWithLimit(offset, limit));
        items.setCount(((ConferenceDAO)conference).countConference());
        return items;
    }
}
