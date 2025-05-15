
package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.authorization.sfs.configuration;

import org.springframework.web.cors.CorsConfiguration;

import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.tokens.jwt.BearerTokenService;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import java.util.List;

/**
 * Configuration class for Spring Security.
 * Sets up authentication, authorization, CORS, and security filters for the application.
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final BearerTokenService tokenService;
    private final BCryptHashingService hashingService;
    private final AuthenticationEntryPoint unauthorizedRequestHandler;

    /**
     * Constructs the security configuration with required dependencies.
     *
     * @param userDetailsService       the service to load user-specific data
     * @param tokenService             the service for handling Bearer tokens
     * @param hashingService           the service for password hashing
     * @param authenticationEntryPoint the entry point for unauthorized requests
     */
    public WebSecurityConfiguration(
            @Qualifier("defaultUserDetailsService") UserDetailsService userDetailsService,
            BearerTokenService tokenService,
            BCryptHashingService hashingService,
            AuthenticationEntryPoint authenticationEntryPoint) {

        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
        this.unauthorizedRequestHandler = authenticationEntryPoint;
    }

    /**
     * Creates the Bearer Authorization Request Filter bean.
     * This filter processes Bearer token authentication for incoming requests.
     *
     * @return the BearerAuthorizationRequestFilter bean
     */
    @Bean
    public BearerAuthorizationRequestFilter authorizationRequestFilter() {
        return new BearerAuthorizationRequestFilter(tokenService, userDetailsService);
    }

    /**
     * Creates the AuthenticationManager bean.
     * Used to process authentication requests.
     *
     * @param authenticationConfiguration the authentication configuration
     * @return the AuthenticationManager bean
     * @throws Exception if an error occurs during creation
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Creates the DaoAuthenticationProvider bean.
     * Configures the provider with user details service and password encoder.
     *
     * @return the DaoAuthenticationProvider bean
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(hashingService);

        return authenticationProvider;
    }

    /**
     * Creates the PasswordEncoder bean.
     * Delegates password encoding to the hashing service.
     *
     * @return the PasswordEncoder bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return hashingService;
    }

    /**
     * Configures the security filter chain and HTTP security.
     * Sets up CORS, CSRF, session management, exception handling, and request authorization.
     *
     * @param http the HttpSecurity to configure
     * @return the SecurityFilterChain bean
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(configurer -> configurer
                .configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedOrigins(List.of("*"));
                    cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                    cors.setAllowedHeaders(List.of("*"));
                    return cors;
                }));

        http.csrf(csrfConfigurer -> csrfConfigurer.disable())
                .exceptionHandling(exceptionHandling
                        -> exceptionHandling.authenticationEntryPoint(unauthorizedRequestHandler))

                .sessionManagement(customizer
                        -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorizeRequests
                        -> authorizeRequests
                        .requestMatchers(
                                "/api/v1/authentication/**",
                                "/api/v1/roles/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/webjars/**").permitAll()
                        .anyRequest().authenticated());
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authorizationRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}