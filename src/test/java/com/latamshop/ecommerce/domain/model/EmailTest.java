package com.latamshop.ecommerce.domain.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.latamshop.ecommerce.application.service.EmailValidationService;
import com.latamshop.ecommerce.domain.model.user.Email;
import com.latamshop.ecommerce.domain.port.EmailValidatorPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class EmailTest {

  private EmailValidatorPort emailValidatorPort;
  private EmailValidationService service;

  @ParameterizedTest
  @ValueSource(strings = {"invalidemail.com"})
  void invalidFormat(String invalidEmail) {
    assertThatThrownBy(() -> new Email(invalidEmail))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid email");
  }

  @ParameterizedTest
  @ValueSource(strings = {""})
  void emptyValue(String emptyValue) {
    assertThatThrownBy(() -> new Email(emptyValue))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Email cannot be empty");
  }

  @BeforeEach
  void setUp() {
    emailValidatorPort = Mockito.mock(EmailValidatorPort.class);
    service = new EmailValidationService(emailValidatorPort);
  }

  @Test
  void ShouldBeFalseEmailInvalidOrTemp() {
    String emailTemporal = "test@yopmail.com";

    when(emailValidatorPort.isReal(emailTemporal)).thenReturn(false);

    assertThrows(
        RuntimeException.class,
        () -> {
          service.validateEmail(emailTemporal);
        });
  }

  @ParameterizedTest
  @ValueSource(strings = {"test@diego.com"})
  void ShouldBeInvalidEmail(String emptyValue) {
    assertThatThrownBy(() -> service.validateEmail(emptyValue))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("Email invalid or temp");
  }
}
