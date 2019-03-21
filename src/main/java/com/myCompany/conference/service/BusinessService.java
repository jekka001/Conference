package com.myCompany.conference.service;

import com.myCompany.conference.entity.Conference;
import com.myCompany.conference.model.Items;

public interface BusinessService {

    Items<Conference> listConference(int offset, int limit);
}
