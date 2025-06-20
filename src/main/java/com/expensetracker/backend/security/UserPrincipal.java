package com.expensetracker.backend.security;

import com.expensetracker.backend.entity.Role;
import com.expensetracker.backend.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal implements UserDetails {

    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.user = user;

        this.authorities = user.getRoles().stream()
                .map(Role::getName)   // RoleName enum
                .map(Enum::name)      // Convert enum to String like "ROLE_USER"
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Customize if you want account expiration logic
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Customize if you want account locking logic
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Customize if you want credentials expiration logic
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Customize if you want to disable users
        return true;
    }
}
