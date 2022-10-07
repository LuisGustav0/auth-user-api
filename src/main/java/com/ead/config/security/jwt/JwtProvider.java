package com.ead.config.security.jwt;

import com.ead.config.security.userdetails.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Log4j2
@Component
public class JwtProvider {

    @Value("${ead.auth.jwtSecret}")
    private String jwtSecret;

    @Value("${ead.auth.jwtExpirationMs}")
    private int jwtExpirationMs;

    private Date getDateExpiration(final Calendar calendar) {
        calendar.setTimeInMillis(calendar.getTimeInMillis() + jwtExpirationMs);

        return calendar.getTime();
    }

    public String generateJwt(Authentication authentication) {
        UserDetails userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Calendar calendar = Calendar.getInstance();

        final Date issuedAt = calendar.getTime();
        final Date expiration = getDateExpiration(calendar);

        return Jwts.builder()
                   .setSubject(userPrincipal.getUsername())
                   .setIssuedAt(issuedAt)
                   .setExpiration(expiration)
                   .signWith(SignatureAlgorithm.HS512, jwtSecret)
                   .compact();
    }

    public String getUsernameJwt(final String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean notValidateJwt(final String token) {
        return !this.validateJwt(token);
    }

    private boolean validateJwt(final String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature: {}", ex);
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token: {}", ex);
        } catch (ExpiredJwtException ex) {
            log.error("JWT token is expired: {}", ex);
        } catch (UnsupportedJwtException ex) {
            log.error("JWT token is unsupported: {}", ex);
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty: {}", ex);
        }
        return false;
    }
}
