package com.latamshop.ecommerce.domain.model.user;

import java.util.regex.Pattern;

public record Password (String value) {

    private static final Pattern VALID_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{8,}$");

    public Password {
        if (value == null || !VALID_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and contain both letters and numbers");
        }
    }

    // metodo para evitar que se muestre en logs
    @Override
    public String toString() {
        return "********";
    }
}