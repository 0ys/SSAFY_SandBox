package com.ssafy.sandbox.smtp.service;

public interface EmailService {
    public boolean sendVerificationCode(String email);
    public boolean verifyCode(String code);
}
