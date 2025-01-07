/**
 * AuthController is a REST controller responsible for handling authentication operations.
 * It provides endpoints for user login, token refresh, and user logout.
 *
 * @author Tejas_Medade
 */
package com.synchrony.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synchrony.configurations.JWT.request.JwtAuthRequest;
import com.synchrony.configurations.JWT.response.JwtAuthResponse;
import com.synchrony.serviceImplementations.UserDetailsServiceImplementation;
import com.synchrony.utils.JWT.JWTUtils;
import com.synchrony.utils.responseHandlers.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The AuthController class handles the authentication-related operations such as user login,
 * token refresh, and user logout.
 */
@RestController
@RequestMapping("/synchrony/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final UserDetailsServiceImplementation userDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTUtils jwtUtils, UserDetailsServiceImplementation userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    /**
     * POST endpoint for user authentication and token generation.
     * Authenticates the user with username and password, then generates and returns JWT tokens.
     *
     * @param authenticationRequest contains username and password
     * @return JwtAuthResponse containing the JWT access token and user details
     */
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody JwtAuthRequest authenticationRequest) {
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        // Load user details after authentication
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        // Generate JWT token and refresh token
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        ResponseCookie refreshJwtCookie = jwtUtils.generateRefreshJwtCookie(userDetails);

        // Create response with JWT token and user details
        JwtAuthResponse response = new JwtAuthResponse(jwtCookie.getValue(), userDetails.getUsername(), userDetails.getAuthorities());

        return ResponseEntity.ok().header("Set-Cookie", jwtCookie.toString()).header("Set-Cookie", refreshJwtCookie.toString()).body(response);
    }

    /**
     * POST endpoint for refreshing access token using refresh token.
     * Validates the refresh token and generates new access and refresh tokens.
     *
     * @param request the HTTP request containing the refresh token
     * @return JwtAuthResponse containing the new JWT access token and user details
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<JwtAuthResponse> refreshToken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getRefreshJwtFromCookies(request);

        if (refreshToken != null && jwtUtils.validateJwtToken(refreshToken)) {
            String username = jwtUtils.getUserNameFromJwtToken(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Generate new access token and refresh token
            ResponseCookie newJwtCookie = jwtUtils.generateJwtCookie(userDetails);
            ResponseCookie newRefreshJwtCookie = jwtUtils.generateRefreshJwtCookie(userDetails);

            // Return new JWT and refresh token cookies
            JwtAuthResponse response = new JwtAuthResponse(newJwtCookie.getValue(), userDetails.getUsername(), userDetails.getAuthorities());
            return ResponseEntity.ok().header("Set-Cookie", newJwtCookie.toString()).header("Set-Cookie", newRefreshJwtCookie.toString()).body(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    /**
     * POST endpoint for logging out the user by invalidating their JWT token.
     * Clears the JWT cookies and returns a response indicating success or failure.
     *
     * @param request the HTTP request containing the JWT token
     * @return ApiResponse indicating the result of the logout action
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletRequest request) {
        String token = jwtUtils.getJwtFromCookies(request);

        if (token != null) {
            String username = jwtUtils.getUserNameFromJwtToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                ApiResponse response = new ApiResponse(
                        LocalDateTime.now(),
                        "User logged out successfully.",
                        true
                );
                return ResponseEntity.ok(response);
            }
        }

        ApiResponse response = new ApiResponse(
                LocalDateTime.now(),
                "Invalid token or User.",
                false
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
