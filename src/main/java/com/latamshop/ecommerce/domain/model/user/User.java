package com.latamshop.ecommerce.domain.model.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @EqualsAndHashCode.Include
    private final UserId id;
    private String name;
    private String email; // replace later with Email value object
    private Password password;
    private String phone;
    private String avatarUrl;
    private boolean emailVerified;
    private boolean active;
    private LocalDateTime lastLogin;
    private final LocalDateTime createdAt;

    private User(UserId id, String name, String email,
                Password password, String phone, String avatarUrl,
                boolean emailVerified, boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
        this.emailVerified = emailVerified;
        this.active = active;
        this.createdAt = createdAt;
    }

    public static User registerNew(String name, String email, Password password, String phone) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be null or blank");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email cannot be null or blank");
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("phone cannot be null or blank");

    return new User(
                UserId.generate(),
                name,
                email,
                password,
                phone,
                null,
                false,
                true,
                LocalDateTime.now()
        );
    }

    public void deactivate() { this.active = false; } // method to deactivate a user
    public void reactivate() { this.active = true; } // method to reactivate a user
}