package com.maher.section345.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest
                                                                          request) {
                        CorsConfiguration config = new CorsConfiguration();

                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and()
                .authorizeHttpRequests()
                //consulter tous les equipes
                .requestMatchers("/api/all/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/api/getbyid/**")
                .hasAnyAuthority("ADMIN", "USER")

.requestMatchers(HttpMethod.POST, "/api/addequipe/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/addleg/**").hasAuthority("ADMIN")
//modifier un produit
                .requestMatchers(HttpMethod.PUT, "/api/updateequipe/**").hasAuthority("ADMIN")
//supprimer un produit
                .requestMatchers(HttpMethod.DELETE, "/api/deleteequipe/**").hasAuthority("ADMIN")
                .anyRequest().authenticated().and()
                .addFilterBefore(new
                        JWTAuthorizationFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }
}

