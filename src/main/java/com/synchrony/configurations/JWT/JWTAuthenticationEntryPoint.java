/**
 * JWTAuthenticationEntryPoint is an implementation of the AuthenticationEntryPoint interface.
 * It handles the unauthorized access to an API by checking if the user has the appropriate access
 * and returns an appropriate response with error details.
 *
 * @author Tejas_Medade
 */
package com.synchrony.configurations.JWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JWTAuthenticationEntryPoint is responsible for handling unauthorized requests by sending
 * a response containing error details when authentication fails.
 * This class is the first point of JWT filtration, checking if the user has access to the requested API.
 */
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * This method is invoked when an unauthenticated user tries to access a protected API.
     * It returns a JSON response with status, error message, and the request path.
     *
     * @param request The HttpServletRequest containing the request details.
     * @param response The HttpServletResponse where the error response will be sent.
     * @param authException The AuthenticationException that provides details about the authentication failure.
     * @throws IOException If an I/O error occurs while writing the response.
     * @throws ServletException If a servlet error occurs during the process.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // Set content type to JSON for the response
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Set the status to Unauthorized (401)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Create a map to hold the response body with error details
        final Map<String, Object> body = new HashMap<>();

        // Populate the error details
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", authException.getMessage());
        body.put("path", request.getServletPath());

        // Convert the map to JSON and send it in the response
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
