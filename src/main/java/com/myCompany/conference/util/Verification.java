package com.myCompany.conference.util;

public interface Verification {

    boolean fieldString(String text);
    boolean fieldEmail(String email);
    boolean fieldPhone(String phone);
    boolean fieldRole(String role);
    boolean fieldLong(String number);
}
