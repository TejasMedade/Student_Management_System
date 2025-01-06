/**
 * WebSecurityConfiguration configures the security settings for the application.
 * It defines authentication, authorization rules, password encoding, JWT filtering, and session management.
 *
 * @author Tejas_Medade
 */
package com.synchrony.configurations.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The WebSecurityConfiguration class defines the security configurations for the web application.
 * This includes setting up CORS, CSRF, authentication and authorization rules, and stateless session management.
 * It also configures the authentication filter and exception handling.
 */
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

    // Public URLs that can be accessed without authentication
    public static final String[] PUBLIC_URLS = {
            "/synchrony/auth/login", // Login endpoint
            "/v3/api-docs", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**" // Swagger UI and API docs
    };

    @Autowired
    private UserDetailsService userDetailsService; // Custom UserDetailsService for user authentication

    @Autowired
    private JWTAuthenticationEntryPoint authenticationEntryPoint; // Entry point for authentication failures

    /**
     * Configures HTTP security for the application. It sets up authentication, authorization rules,
     * session management, and adds the JWT authentication filter.
     *
     * @param http The HttpSecurity object used to configure security.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Enable CORS with default configuration
                .csrf(csrf -> csrf.disable()) // Disable CSRF (stateless applications)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(PUBLIC_URLS).permitAll() // Public endpoints that don't require authentication
                        .requestMatchers("/synchrony/admin/**").hasRole("ADMIN") // Admin role access
                        .requestMatchers("/synchrony/student/**").hasRole("STUDENT") // Student role access
                        .requestMatchers("/synchrony/auth/logout").authenticated() // Require authentication for logout
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session (no server-side session storage)
                )
                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(authenticationEntryPoint) // Handle authentication exceptions
                )
                .authenticationProvider(authenticationProvider()) // Use custom AuthenticationProvider for user authentication
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); // Add JWT filter before UsernamePasswordAuthenticationFilter
        return http.build();
    }

    /**
     * Configures a custom authentication provider with a UserDetailsService and password encoder.
     *
     * @return The configured DaoAuthenticationProvider.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Set the UserDetailsService and PasswordEncoder for authentication
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * Configures a password encoder using BCrypt.
     *
     * @return The configured BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the authentication manager, which is used for authenticating users.
     *
     * @param authConfig The AuthenticationConfiguration to configure the manager.
     * @return The configured AuthenticationManager.
     * @throws Exception If an error occurs during the configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configures the JWT authentication filter, which is used to validate JWT tokens in incoming requests.
     *
     * @return The configured JWTAuthenticationFilter.
     */
    @Bean
    public JWTAuthenticationFilter authenticationJwtTokenFilter() {
        return new JWTAuthenticationFilter();
    }
}
