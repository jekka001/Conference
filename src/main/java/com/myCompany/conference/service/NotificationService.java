package com.myCompany.conference.service;

public interface NotificationService {

    void sendNotification(String title, String content, String toEmail, String fromEmail);
}
