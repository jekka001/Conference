package com.myCompany.conference.service;

public interface EncryptionService {
    int generationSalt();
    String encryption(String password);
    boolean checkPassword(String userPassword, String bdPassword);
}
