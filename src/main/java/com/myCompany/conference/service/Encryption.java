package com.myCompany.conference.service;

public interface Encryption {
    int generationSalt();
    String encryption(String password);
    boolean checkPassword(String userPassword, String bdPassword);
}
