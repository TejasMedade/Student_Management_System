package com.synchrony.utils.JWT;

import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility class for handling JWT (JSON Web Token) operations.
 * <p>
 * This class provides methods to generate, validate, and parse JWT tokens, along with managing
 * JWT cookies for access and refresh tokens.
 * </p>
 *
 * Author: @Tejas_Medade
 */
@Component
public class JWTUtils {

    // JWT Configuration properties loaded from application.properties
    @Value("${jwtSecret}")
    private String secret;

    @Value("${jwtTokenValidity}")
    private int tokenValidity;

    @Value("${jwtRefreshTokenValidity}")
    private int refreshTokenValidity;

    @Value("${jwtCookieName}")
    private String jwtCookie;

    @Value("${jwtRefreshCookieName}")
    private String jwtRefreshCookie;

    // Logger to log errors and warnings related to JWT processing
    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    /**
     * Get the access token from the cookies.
     * This method checks for the JWT access token in the request cookies.
     *
     * @param request The HTTP request containing the cookies
     * @return The JWT access token if present, otherwise null
     */
    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * Get the refresh token from the cookies.
     * This method checks for the JWT refresh token in the request cookies.
     *
     * @param request The HTTP request containing the cookies
     * @return The JWT refresh token if present, otherwise null
     */
    public String getRefreshJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtRefreshCookie);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * Generate the access token JWT with a short expiry time.
     * This method creates a JWT token for access with a specified validity duration.
     *
     * @param userDetails The user details (used to extract the username for the token)
     * @return A ResponseCookie containing the JWT token for access
     */
    public ResponseCookie generateJwtCookie(UserDetails userDetails) {
        String jwt = generateTokenFromUsername(userDetails.getUsername(), tokenValidity); // Default expiry time
        return ResponseCookie.from(jwtCookie, jwt)
                .path("/synchrony")  // Path for the cookie
                .maxAge(tokenValidity)  // Set expiry (20 minutes by default)
                .httpOnly(true)  // Prevent client-side access to the cookie
                .secure(true)  // Only send over HTTPS
                .sameSite("Strict")  // Protect against CSRF attacks
                .build();
    }

    /**
     * Generate the refresh token JWT with a longer expiry time.
     * This method creates a JWT token for refresh with a longer validity duration.
     *
     * @param userDetails The user details (used to extract the username for the token)
     * @return A ResponseCookie containing the JWT token for refresh
     */
    public ResponseCookie generateRefreshJwtCookie(UserDetails userDetails) {
        String refreshToken = generateTokenFromUsername(userDetails.getUsername(), refreshTokenValidity); // 1 day expiry
        return ResponseCookie.from(jwtRefreshCookie, refreshToken)
                .path("/synchrony")  // Path for the cookie
                .maxAge(refreshTokenValidity)  // Set expiry (1 day by default)
                .httpOnly(true)  // Prevent client-side access to the cookie
                .secure(true)  // Only send over HTTPS
                .sameSite("Strict")  // Protect against CSRF attacks
                .build();
    }

    /**
     * Clear the access token cookie.
     * This method generates a response cookie with a null value to delete the JWT access token.
     *
     * @return A ResponseCookie for clearing the JWT access token cookie
     */
    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie.from(jwtCookie, null).path("/synchrony").build();
    }

    /**
     * Clear the refresh token cookie.
     * This method generates a response cookie with a null value to delete the JWT refresh token.
     *
     * @return A ResponseCookie for clearing the JWT refresh token cookie
     */
    public ResponseCookie getCleanRefreshJwtCookie() {
        return ResponseCookie.from(jwtRefreshCookie, null).path("/synchrony").build();
    }

    /**
     * Extract the username from a JWT token.
     * This method decodes the JWT token and retrieves the username (subject).
     *
     * @param token The JWT token to be parsed
     * @return The username extracted from the token
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validate the JWT token.
     * This method checks the validity of the provided JWT token by verifying its signature,
     * expiration, and other claims.
     *
     * @param authToken The JWT token to be validated
     * @return true if the token is valid, false otherwise
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).build().parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Generate a JWT token from the username with a specified expiry time.
     * This method creates a new JWT token based on the username and sets the expiration time.
     *
     * @param username      The username for the token's subject
     * @param validityMinutes The validity duration of the token in minutes
     * @return The generated JWT token as a string
     */
    private String generateTokenFromUsername(String username, int validityMinutes) {
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        Date expirationDate = new Date(timeInMillis + (validityMinutes * 60 * 1000));
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
