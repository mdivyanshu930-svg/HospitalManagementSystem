package com.codingbydivyanshu.yt.HospitalManagement.security;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.LoginResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;
    private final AuthService authService;
    @Override
    //user login hone ke baad ye method automatically call hoga
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token= (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User=((OAuth2AuthenticationToken) authentication).getPrincipal();
        String resgistrationId=token.getAuthorizedClientRegistrationId();
        ResponseEntity<LoginResponseDto> loginREsponse=authService
                .handleoauth2LoginRequest(oAuth2User,resgistrationId);
        response.setStatus(loginREsponse.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(loginREsponse.getBody()));
    }
}
