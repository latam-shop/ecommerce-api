package com.latamshop.ecommerce.application.service;

import com.latamshop.ecommerce.domain.model.user.Email;
import com.latamshop.ecommerce.domain.port.EmailValidatorPort;
import org.springframework.stereotype.Service;

@Service
public class EmailValidationService {
    private final EmailValidatorPort emailValidator;

    public EmailValidationService(EmailValidatorPort emailValidator) {
        this.emailValidator = emailValidator;
    }

    public void validateEmail(String emailInput) {
        Email email = new Email(emailInput);

        if (!emailValidator.isReal(email.value())) {
            throw new RuntimeException("Email invalid or temp");
        }
    }
}
