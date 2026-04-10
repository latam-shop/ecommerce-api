package com.latamshop.ecommerce.domain.model;

import com.latamshop.ecommerce.domain.model.user.Email;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EmailTest {
    @ParameterizedTest
    @ValueSource(strings = { "invalidemail.com" })
    void invalidFormat(String invalidEmail){
        assertThatThrownBy(() ->
                new Email(invalidEmail)).isInstanceOf(IllegalArgumentException.class)
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