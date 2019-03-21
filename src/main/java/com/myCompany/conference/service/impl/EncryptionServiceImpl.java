package com.myCompany.conference.service.impl;

import com.myCompany.conference.service.Encryption;
import org.jasypt.util.password.BasicPasswordEncryptor;

import java.security.SecureRandom;

public class EncryptionServiceImpl implements Encryption {
    public static EncryptionServiceImpl instance = new EncryptionServiceImpl();
    private SecureRandom secureRandom = new SecureRandom();
    private BasicPasswordEncryptor basicPasswordEncryptor = new BasicPasswordEncryptor();

    private EncryptionServiceImpl() {
    }

    @Override
    public int generationSalt() {
        return secureRandom.nextInt();
    }

    @Override
    public String encryption(String password) {
        return basicPasswordEncryptor.encryptPassword(password);
    }

    @Override
    public boolean checkPassword(String userPassword, String bdPassword) {
        return basicPasswordEncryptor.checkPassword(userPassword, bdPassword);
    }
}
