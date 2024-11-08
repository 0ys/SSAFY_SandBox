package com.ssafy.sandbox.smtp.controller;

import com.ssafy.sandbox.smtp.dto.EmailRequestDto;
import com.ssafy.sandbox.smtp.dto.VerificationRequestDto;
import com.ssafy.sandbox.smtp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequestDto requestDto) {
        boolean isSent = emailService.sendVerificationCode(requestDto.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("isOk", isSent));
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> verifyCode(@RequestBody VerificationRequestDto requestDto) {
        boolean isVerified = emailService.verifyCode(requestDto.getAuthentication());
        return ResponseEntity.ok(Collections.singletonMap("isSuccess", isVerified));
    }
}
