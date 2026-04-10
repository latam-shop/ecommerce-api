package com.latamshop.ecommerce.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {

    private final UUID id;

    private final String name;
    private final String email;
    private final String password;
    private final String phone;
    private final String avatarUrl;
    private final boolean emailVerified;
    private boolean active;
    private LocalDateTime lastLogin;
    private final LocalDateTime createdAt;

    public User(UUID id, String name, String email,
                String password, String phone, String avatarUrl,
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

    public static User registerNew(String name, String email, String password, String phone) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be null or blank");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email cannot be null or blank");
        if (password == null || password.isBlank()) throw new IllegalArgumentException("password cannot be null or blank");
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("phone cannot be null or blank");

        return new User(
                UUID.randomUUID(),
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

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getAvatarUrl() { return avatarUrl; }
    public boolean isEmailVerified() { return emailVerified; }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; } // metodo para desactivar un usuario
    public void reactive() { this.active = true; } // metodo para reactivar un usuario
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}