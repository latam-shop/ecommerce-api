package com.latamshop.ecommerce.domain.model.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class UserTest {

  @Test void generate_shouldReturnNonNullUserId() {
    UserId userId = UserId.generate();
    assertThat(userId).isNotNull();
  }

  @Test void generate_shouldReturnUniqueIds() {
    UserId first = UserId.generate();
    UserId second = UserId.generate();
    assertThat(first).isNotEqualTo(second);
  }

  // verifies successful creation and default states (active, unverified email).
  @Test
  void registerUser() {
    User user =
        User.registerNew("Juan", "juan@example.com", new Password("12345678Abd#"), "3001234567");

    assertThat(user.getId()).isNotNull();
    assertThat(user.getName()).isEqualTo("Juan");
    assertThat(user.isActive()).isTrue();
    assertThat(user.isEmailVerified()).isFalse();
  }

  // verifies that blank names are rejected with the correct exception.
  @Test
  void failOnBlankName() {
    assertThatThrownBy(
            () ->
                User.registerNew(
                    "   ", "juan@example.com", new Password("12345678Abd#"), "3001234567"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("name cannot be null or blank");
  }

  // verifies that deactivate() turns off the user's active flag.
  @Test
  void deactivateUser() {
    User user =
        User.registerNew("Juan", "juan@example.com", new Password("12345678Abd#"), "3001234567");

    user.deactivate();

    assertThat(user.isActive()).isFalse();
  }

  // verifies that an inactive user becomes active again using reactivate().
  @Test
  void reactivateUser() {
    User user =
        User.registerNew("Juan", "juan@latamshop.com", new Password("12345678Abd#"), "3001234567");
    user.deactivate();

    user.reactivate();

    assertThat(user.isActive()).isTrue();
  }
}
