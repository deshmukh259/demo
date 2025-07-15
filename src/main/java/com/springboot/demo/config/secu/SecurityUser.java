package com.springboot.demo.config.secu;

import com.springboot.demo.model.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SecurityUser implements org.springframework.security.core.userdetails.UserDetails {

    private UserDetails userDetails;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getUserAuthorities().stream().map(e -> {
            GrantedAuthority grantedAuthority1 = () -> e.getName();
            return grantedAuthority1;
        }).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetails.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
