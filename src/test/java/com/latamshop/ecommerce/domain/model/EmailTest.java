package com.latamshop.ecommerce.domain.model;

import com.latamshop.ecommerce.domain.model.user.Email;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EmailTest {
    @ParameterizedTest
    @ValueSource(strings = { "correoinvalido.com" })
    void invalidFormat(String emailInvalido){
        assertThatThrownBy(() ->
                new Email(emailInvalido)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid email");
    }

    @ParameterizedTest
    @ValueSource(strings = { "" })
    void emptyValue(String emptyValue){
        assertThatThrownBy(() ->
                new Email(emptyValue)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email cannot be empty");
    }
}