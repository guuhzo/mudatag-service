package br.com.gsndev.mudatag.user.dto;

import java.util.UUID;

public record BaseUserDTO(
        UUID id,
        String fullName,
        boolean blocked,
        String authId
) {}