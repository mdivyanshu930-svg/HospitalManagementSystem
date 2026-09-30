package com.codingbydivyanshu.yt.HospitalManagement.Controller;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.LoginRequestDto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.LoginResponseDto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.SignUpResponsedto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.SignuprequestDto;
import com.codingbydivyanshu.yt.HospitalManagement.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponsedto> signup(@RequestBody SignuprequestDto signupRequestDto){
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}
