package com.codingbydivyanshu.yt.HospitalManagement.security;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.LoginRequestDto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.LoginResponseDto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.SignUpResponsedto;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.UserRepository;
import com.codingbydivyanshu.yt.HospitalManagement.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {
    private final AuthUtil authUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                (loginRequestDto.getUsername(),loginRequestDto.getPassword()));
        User user=(User) authentication.getPrincipal();
        String token=authUtil.generateAccessToken(user);
        return new LoginResponseDto(token,user.getId());

    }
    public SignUpResponsedto signup(LoginRequestDto signuprequestdto){
        User user=userRepository.findByUsername(signuprequestdto.getUsername()).orElse(null);
         if(user!=null){throw new IllegalArgumentException("User all ready exists");}
             user=userRepository.save(User.builder().
                     username(signuprequestdto.getUsername()).password(passwordEncoder.encode(signuprequestdto.getPassword())).
                     build());
         return new SignUpResponsedto(user.getId(), user.getUsername());
    }

}
