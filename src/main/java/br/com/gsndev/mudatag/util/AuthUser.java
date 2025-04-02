package br.com.gsndev.mudatag.util;

import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AuthUser {

    public static String getAuthId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public static BaseUserDTO getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (BaseUserDTO) authentication.getDetails();
    }

}
