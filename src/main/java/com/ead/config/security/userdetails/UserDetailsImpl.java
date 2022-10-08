package com.ead.config.security.userdetails;

import com.ead.model.AuthorityModel;
import com.ead.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private UUID id;
    private String fullName;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    private static SimpleGrantedAuthority toSimpleGrantedAuthority(final AuthorityModel authority) {
        return new SimpleGrantedAuthority(authority.getAuthority());
    }

    private static Collection<? extends GrantedAuthority> getListGrantedAuthority(final UserModel userModel) {
        return userModel.getListAuthority()
                        .stream()
                        .map(UserDetailsImpl::toSimpleGrantedAuthority)
                        .collect(Collectors.toSet());
    }

    public static UserDetailsImpl build(final UserModel userModel) {
        final var authorities = getListGrantedAuthority(userModel);

        return UserDetailsImpl.builder()
                              .id(userModel.getId())
                              .fullName(userModel.getFullName())
                              .username(userModel.getLogin())
                              .password(userModel.getPassword())
                              .email(userModel.getEmail())
                              .authorities(authorities)
                              .build();
    }
}
