package com.codingbydivyanshu.yt.HospitalManagement.security;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

import static com.codingbydivyanshu.yt.HospitalManagement.type.PermissionType.APPOINTMENT_DELETE;
import static com.codingbydivyanshu.yt.HospitalManagement.type.PermissionType.USER_MANAGE;
import static com.codingbydivyanshu.yt.HospitalManagement.type.RoleType.*;
import static com.codingbydivyanshu.yt.HospitalManagement.type.PermissionType.*;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity

public class WebSecurityConfig {
   // private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final HandlerExceptionResolver handlerExceptionResolver;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
          httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement
                        (sessionConfig -> sessionConfig
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/public/**")
                        .permitAll()

                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
                        .requestMatchers("/doctor/**").hasAnyRole(DOCTOR.name(), ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE,"/admin/**")
                        .hasAnyAuthority(APPOINTMENT_DELETE.name(),USER_MANAGE.name())
                        .anyRequest().authenticated())

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .failureHandler((request, response, exception)
                                -> {
                            log.error("OAuth2 Error: {}", exception.getMessage());
                            handlerExceptionResolver.resolveException(request,response,null,exception);


                        })
                        .successHandler(oAuth2SuccessHandler)
                ).exceptionHandling(exceptionHandlingConfigurer  -> {
                    exceptionHandlingConfigurer.accessDeniedHandler(new AccessDeniedHandler() {
                        @Override
                        public void handle(HttpServletRequest request, HttpServletResponse response,
                                           AccessDeniedException accessDeniedException) throws
                                IOException, ServletException {
                            handlerExceptionResolver.resolveException(request,response,null,accessDeniedException);


                        }
                    });
                });

        //         .formLogin(Customizer.withDefaults());
        return httpSecurity.build();

    }



}
