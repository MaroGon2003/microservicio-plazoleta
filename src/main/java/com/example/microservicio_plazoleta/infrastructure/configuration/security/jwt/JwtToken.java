package com.example.microservicio_plazoleta.infrastructure.configuration.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class JwtToken {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtToken.class);
    private static final String ROLE = "rol";
    public static final String ACCESS_TOKEN_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXvCJ9";

    private JwtToken () {}

    public static UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            String role = (String) claims.get(ROLE);
            Collection<? extends GrantedAuthority> authorities =
                    Collections.singletonList(new SimpleGrantedAuthority(role));
            return new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    authorities
            );
        }
        catch (JwtException e) {
            return null;
        }
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(ACCESS_TOKEN_SECRET.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            LOGGER.error("Malformed token");
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Unsupported token");
        } catch (ExpiredJwtException e) {
            LOGGER.error("Expired token");
        } catch (IllegalArgumentException e) {
            LOGGER.error("Empty token");
        } catch (SecurityException e) {
            LOGGER.error("Signature failure");
        }
        return false;
    }

}
