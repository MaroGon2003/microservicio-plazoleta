package com.example.microservicio_plazoleta.infrastructure.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.example.microservicio_plazoleta.infrastructure.configuration.security.jwt.JwtAuthorizationFilter.getToken;
import static com.example.microservicio_plazoleta.infrastructure.configuration.security.jwt.JwtToken.ACCESS_TOKEN_SECRET;

public class JwtAuthorizationExtractor {

    private JwtAuthorizationExtractor() {
    }

    public static Long extractUserId() {

        Claims claims = getClaims();

        return claims.get("id", Long.class);

    }

    public static String extractUserRol() {
        Claims claims = getClaims();
        return claims.get("rol", String.class);
    }

    private static Claims getClaims() {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes == null) {
            throw new NullPointerException();
        }

        HttpServletRequest request = requestAttributes.getRequest();

        String token = getToken(request);

        return Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
