/**
 * JWTAuthenticationFilter is responsible for processing JWT tokens in incoming HTTP requests.
 * It validates the access token and refresh token, setting the authentication context if valid.
 * It also handles refreshing expired access tokens using the refresh token.
 *
 * @author Tejas_Medade
 */
package com.synchrony.configurations.JWT;

import com.synchrony.utils.JWT.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * The JWTAuthenticationFilter intercepts HTTP requests, validates the provided JWT tokens (access token or refresh token),
 * and sets the user authentication in the security context. If the access token is expired, it attempts to refresh it
 * using the refresh token.
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    /**
     * This method is invoked for each incoming HTTP request. It retrieves the JWT tokens from cookies,
     * validates them, and sets the user authentication if valid. If the access token is expired, it attempts to
     * refresh the token using a valid refresh token.
     *
     * @param request The HTTP request that may contain JWT tokens.
     * @param response The HTTP response that will be modified to add new cookies if needed.
     * @param filterChain The filter chain to pass the request along after processing.
     * @throws ServletException If an error occurs during servlet processing.
     * @throws IOException If an I/O error occurs while processing the request.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            // Retrieve JWT tokens from the request cookies
            String jwt = jwtUtils.getJwtFromCookies(request);
            String refreshToken = jwtUtils.getRefreshJwtFromCookies(request);

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                // If access token is valid, authenticate the user
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Set authentication details in the security context
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else if (refreshToken != null && jwtUtils.validateJwtToken(refreshToken)) {
                // If access token is expired, use the refresh token to generate a new access token
                String username = jwtUtils.getUserNameFromJwtToken(refreshToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Generate new access and refresh tokens
                ResponseCookie newAccessTokenCookie = jwtUtils.generateJwtCookie(userDetails);
                ResponseCookie newRefreshTokenCookie = jwtUtils.generateRefreshJwtCookie(userDetails);

                // Convert ResponseCookie to javax.servlet.http.Cookie and add them to the response
                Cookie accessTokenCookie = convertResponseCookieToServletCookie(newAccessTokenCookie);
                Cookie refreshTokenCookie = convertResponseCookieToServletCookie(newRefreshTokenCookie);

                response.addCookie(accessTokenCookie);
                response.addCookie(refreshTokenCookie);

                // Set authentication details in the security context
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            // Log any errors during authentication
            logger.error("User Authentication Failed: {}", e);
        }

        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }

    /**
     * This method converts a ResponseCookie (from Spring Security) to a javax.servlet.http.Cookie to be added to the response.
     *
     * @param responseCookie The ResponseCookie to be converted.
     * @return The corresponding javax.servlet.http.Cookie.
     */
    private Cookie convertResponseCookieToServletCookie(ResponseCookie responseCookie) {
        Cookie servletCookie = new Cookie(responseCookie.getName(), responseCookie.getValue());
        servletCookie.setHttpOnly(responseCookie.isHttpOnly());
        servletCookie.setSecure(responseCookie.isSecure());
        servletCookie.setPath(responseCookie.getPath());
        servletCookie.setDomain(responseCookie.getDomain());
        servletCookie.setMaxAge((int) responseCookie.getMaxAge().getSeconds());
        return servletCookie;
    }
}
