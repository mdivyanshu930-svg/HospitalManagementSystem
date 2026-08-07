package com.codingbydivyanshu.yt.HospitalManagement.security;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor

public class WebSecurityConfig {
   // private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement
                        (sessionConfig->sessionConfig
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/patient/**","/auth/**").permitAll()
                                .anyRequest().authenticated()
                 //       .requestMatchers("/patient/**").hasRole("PATIENT")
                      //          .requestMatchers("/doctor/**").hasAnyRole("DOCTOR","PATIENT")
                                ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

       //         .formLogin(Customizer.withDefaults());
        return httpSecurity.build();

    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
