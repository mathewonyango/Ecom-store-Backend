package com.example.demo.user;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.MailSenderService;

@Service
public class PasswordResetService {

    private final PasswordResetRequestRepository passwordResetRequestRepository;
    private final MailSenderService mailSenderService;

    public PasswordResetService(PasswordResetRequestRepository passwordResetRequestRepository, MailSenderService mailSenderService) {
        this.passwordResetRequestRepository = passwordResetRequestRepository;
        this.mailSenderService = mailSenderService;
    }

    public String generateResetToken() {
        final String CHARACTERS = "0123456789";
        final int RANDOM_STRING_LENGTH = 5;

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(RANDOM_STRING_LENGTH);

        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    public void savePasswordReset(String email, String name) {

        RandomGenerator randomGenerator= new RandomGenerator();
        String resetToken=randomGenerator.generateRandomString();
        // String resetToken=generateResetToken();
        String emailBody = "Dear"+ " "+name +"," + "\n\n"
                + "You have requested to reset your password. Please use the following token to complete the process:\n\n"
                + "Reset Token: " + resetToken + "\n\n"
                + "If you did not request this, please ignore this email.\n\n"
                + "Best regards,\n Mathews Onyango & Team";
        mailSenderService.sendNewMail(email,"Password reset Code",emailBody);
        // Find the existing PasswordResetRequest or create a new one if not found
        PasswordResetRequest passwordResetRequest = passwordResetRequestRepository.findByEmail(email)
                .orElseGet(PasswordResetRequest::new);

        // Set the properties
        passwordResetRequest.setEmail(email);

        passwordResetRequest.setName(name);
        passwordResetRequest.setToken(resetToken);
        passwordResetRequest.setTimestamp(LocalDateTime.now());
        // Save the password reset request to the database
        passwordResetRequestRepository.save(passwordResetRequest);
    }

    public Optional<PasswordResetRequest> FindByEmail(String email) {
        return passwordResetRequestRepository.findByEmail(email);
    }
}
