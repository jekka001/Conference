package com.myCompany.conference.service;

import com.myCompany.conference.entity.Conference;
import com.myCompany.conference.entity.User;
import com.myCompany.conference.model.Items;

public interface BusinessService {

    Items<Conference> listConference(int offset, int limit);
    User signIn(String login, String password);
    User registered(String name, String surname, String email, String password);
}
