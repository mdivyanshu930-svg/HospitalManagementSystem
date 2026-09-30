package com.codingbydivyanshu.yt.HospitalManagement.entity;

import com.codingbydivyanshu.yt.HospitalManagement.security.RolePermissionMapping;
import com.codingbydivyanshu.yt.HospitalManagement.type.AuthProviderType;
import com.codingbydivyanshu.yt.HospitalManagement.type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
        @Index(name="idx_provider_id_provider_type",columnList =" provider_id,provider_type")
})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String providerId;
    @Enumerated(EnumType.STRING)
    private AuthProviderType providerType;
    @ElementCollection(fetch =FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<RoleType>roleTypes=new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
        roleTypes.forEach(roleType -> {
            Set<SimpleGrantedAuthority> permissions= RolePermissionMapping.getAuthoritiesForRole(roleType);
            authorities.addAll(permissions);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+roleType.name()));
        });
        return authorities;

//        return roleTypes.stream().map(roleTypes->
  //              new SimpleGrantedAuthority("ROLE_"+roleTypes.name())).collect(Collectors.toSet());
    }
}
