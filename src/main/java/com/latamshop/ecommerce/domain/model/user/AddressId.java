package com.latamshop.ecommerce.domain.model.user;

import java.util.UUID;

public record AddressId(UUID value) {
  public AddressId {
    if (value == null) {
      throw new IllegalArgumentException("Address Id cannot be null");
    }
  }

  public static AddressId generate() {
    return new AddressId(UUID.randomUUID());
  }
}
