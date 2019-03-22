package com.myCompany.conference.service;

public interface NotificationService {

    void sendNotification(String fromEmail, String toEmail, String name, String phone, String message);

    void shutdown();
}
