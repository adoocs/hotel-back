package com.vangogh.hotel.infrastructure.config.security.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank String username,
                               @NotBlank String password) {
}