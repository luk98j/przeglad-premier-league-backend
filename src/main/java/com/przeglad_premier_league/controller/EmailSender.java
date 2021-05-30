package com.przeglad_premier_league.controller;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}
