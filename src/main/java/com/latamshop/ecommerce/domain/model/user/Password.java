package com.latamshop.ecommerce.domain.model.user;

import java.util.regex.Pattern;

public record Password(String value) {
  private static final Pattern VALID_PATTERN =
      Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

  public Password {
    if (value == null || !VALID_PATTERN.matcher(value).matches()) {
      throw new IllegalArgumentException(
          "Contraseña no válida: debe tener 8+ caracteres, letras y números.");
    }
  }

  // metodo para evitar que se muestre en logs
  @Override
  public String toString() {
    return "********";
  }
}
