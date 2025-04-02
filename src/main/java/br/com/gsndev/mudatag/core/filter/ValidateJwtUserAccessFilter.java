package br.com.gsndev.mudatag.core.filter;

import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import br.com.gsndev.mudatag.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class ValidateJwtUserAccessFilter extends OncePerRequestFilter {
    private final JwtDecoder jwtDecoder;
    private final UserService userService;

    public ValidateJwtUserAccessFilter(JwtDecoder jwtDecoder, UserService userService) {
        this.jwtDecoder = jwtDecoder;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.extractToken(request);

        if (token == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bearer token not provided");
            return;
        }

        try {
            Jwt jwt = jwtDecoder.decode(token);

            if (request.getRequestURI().startsWith("/users") && request.getMethod().equals("POST")) {
                filterChain.doFilter(request, response);
                return;
            }

            BaseUserDTO userData = this.userService.findByAuthId(jwt.getSubject()).orElse(null);

            boolean isValidUser = userData != null && !userData.isBlocked();

            if (!isValidUser) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                return;
            }

            var authToken = new JwtAuthenticationToken(jwt, Collections.emptyList());
            authToken.setDetails(userData);

            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (JwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer ", "");
        }

        return null;
    }
}
