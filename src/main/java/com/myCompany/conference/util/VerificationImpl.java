package com.myCompany.conference.util;


import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificationImpl implements Verification {

    @Override
    public boolean fieldString(String text) {
        String regul = "^[0-9A-Za-z]+\\z";
        Pattern patern = Pattern.compile(regul);
        Matcher matcher = patern.matcher(text);
        return matcher.find();
    }

    @Override
    public boolean fieldEmail(String email) {
        String regul = "^[0-9A-Za-z][-0-9A-z._]+[0-9A-Za-z]@([-0-9A-Za-z]+\\.){1,2}[-A-Za-z]{2,}\\z";
        Pattern patern = Pattern.compile(regul);
        Matcher matcher = patern.matcher(email);
        return matcher.find();
    }

    @Override
    public boolean fieldPhone(String phone) {
        String regul = "^\\+[1-9]\\d{1,2}\\(\\d{2}\\)(\\d{2}-){2}\\d{3}\\z";
        Pattern pattern = Pattern.compile(regul);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }

    @Override
    public boolean fieldRole(String role) {
        String requl = "^Admin|Moderator|Speaker|User\\z";
        Pattern pattern = Pattern.compile(requl);
        Matcher matcher = pattern.matcher(role);
        return matcher.find();
    }

    @Override
    public boolean fieldLong(String number) {
        String requl = "^[0-9]+\\z";
        Pattern pattern = Pattern.compile(requl);
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }


}
