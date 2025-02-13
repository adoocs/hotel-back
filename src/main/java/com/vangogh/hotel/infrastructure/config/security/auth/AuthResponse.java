package com.vangogh.hotel.infrastructure.config.security.auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "status", "token"})
public record AuthResponse(
        String username,
        String message,
        String token,
        Boolean status) {
}