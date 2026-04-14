package com.latamshop.ecommerce.domain.model.user;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddressTest {

    @ParameterizedTest
    @NullSource
    void emptyId(UUID emptyId) {
        AssertionsForClassTypes.assertThatThrownBy(() -> new AddressId(emptyId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Address Id cannot be null");
    }

    @Test
    void AddressIdGenerateMethod() {
        AddressId addressId = AddressId.generate();
        assertThat(addressId).isNotNull();
    }

    @Test
    void AddressIDGenerateComparation(){
        AddressId firts = AddressId.generate();
        AddressId second = AddressId.generate();
        assertThat(firts).isNotEqualTo(second);
    }

    @Test
    void failOnBlankCity() {
        assertThatThrownBy(
                () ->
                        Address.registerNew("cl 3","","76003","valle del cauca","Colombia",false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("city cannot be null or empty");
    }

    @Test
    void failOnBlankCountry() {
        assertThatThrownBy(
                () ->
                        Address.registerNew("cl 3","Cali","76003","valle del cauca","",false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("country cannot be null or empty");
    }
}
