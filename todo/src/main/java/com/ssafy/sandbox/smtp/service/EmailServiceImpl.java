package com.ssafy.sandbox.smtp.service;

import com.ssafy.sandbox.smtp.repository.EmailRepository;
import com.ssafy.sandbox.smtp.vo.VerificationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    public boolean sendVerificationCode(String email) {
        String code = generateCode();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your verification code");
            message.setText("Your verification code is " + code);
            mailSender.send(message);
            emailRepository.save(new VerificationCode(email, code));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyCode(String code) {
        Optional<VerificationCode> optionalCode = emailRepository.findByCode(code);
        return optionalCode.isPresent();
    }

    private String generateCode() {
        return String.format("%06d", new Random().nextInt(1000000));
    }
}

