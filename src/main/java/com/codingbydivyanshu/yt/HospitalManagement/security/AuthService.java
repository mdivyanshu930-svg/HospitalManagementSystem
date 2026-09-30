package com.codingbydivyanshu.yt.HospitalManagement.security;

import com.codingbydivyanshu.yt.HospitalManagement.Dto.LoginRequestDto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.LoginResponseDto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.SignUpResponsedto;
import com.codingbydivyanshu.yt.HospitalManagement.Dto.SignuprequestDto;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.PatientRepository;
import com.codingbydivyanshu.yt.HospitalManagement.Repository.UserRepository;
import com.codingbydivyanshu.yt.HospitalManagement.entity.User;
import com.codingbydivyanshu.yt.HospitalManagement.entity.patient;
import com.codingbydivyanshu.yt.HospitalManagement.type.AuthProviderType;
import com.codingbydivyanshu.yt.HospitalManagement.type.RoleType;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor

public class AuthService {
    private final AuthUtil authUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PatientRepository patientRepository;
    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                (loginRequestDto.getUsername(),loginRequestDto.getPassword()));
        User user=(User) authentication.getPrincipal();
        String token=authUtil.generateAccessToken(user);
        return new LoginResponseDto(token,user.getId());

    }
    public SignUpResponsedto signup(SignuprequestDto signuprequestdto){
        User user =signUpInternal(signuprequestdto,AuthProviderType.EMAIL,null);
        return new SignUpResponsedto(user.getId(), user.getUsername());
    }

    public User signUpInternal(SignuprequestDto signuprequestdto, AuthProviderType authProviderType, String providerid){
        User user=userRepository.findByUsername(signuprequestdto.getUsername()).orElse(null);
         if(user!=null){throw new IllegalArgumentException("User all ready exists");}
             user= User.builder().
                     username(signuprequestdto.getUsername()).

                     providerId(providerid).
                     roleTypes(signuprequestdto.getRoleTypes()).
                     providerType(authProviderType).
                     build();
         if(authProviderType==AuthProviderType.EMAIL){
             user.setPassword(passwordEncoder.encode(signuprequestdto.getPassword()));

         }
        userRepository.save(user);
         patient Patient= patient.builder().name(signuprequestdto.getName())
                 .email(signuprequestdto.getUsername()).user(user).build();
            patientRepository.save(Patient);
        return user;

    }

@Transactional
    public ResponseEntity<LoginResponseDto> handleoauth2LoginRequest(OAuth2User oAuth2User, String resgistrationId) {
        AuthProviderType authProviderType=authUtil.getProviderTypeFromRegistrationId(resgistrationId);
        String providerId=authUtil.determineProviderIdwithOAuth2User(oAuth2User,resgistrationId);
        //providertype aur providerid ka pata lagana
        User user=userRepository
                .findByProviderIdAndProviderType(providerId,authProviderType).orElse(null);
        String email=oAuth2User.getAttribute("email");
        String name=oAuth2User.getAttribute("name");
        User emailuser=userRepository.findByUsername(email).orElse(null);
        if(user==null&& emailuser==null){
            String username=authUtil.deterUserNamefromOauth2User(oAuth2User,resgistrationId,providerId);
            user=signUpInternal(new SignuprequestDto(username,name,null,Set.of(RoleType.PATIENT)),authProviderType,providerId);

        } else if (user!=null) {
            if (email!=null && !email.isBlank()&&!email.equals(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);
            }
            
        }
        else {
            throw new BadCredentialsException("This email is already exists with provider"+emailuser.getProviderType());
        }
        LoginResponseDto loginResponseDto=new LoginResponseDto(authUtil.generateAccessToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDto);
        //save the provider id and provider type info with user
        //if user as an account: directly login,otherwise signup then login
    }
}
