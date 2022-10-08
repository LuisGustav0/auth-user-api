package com.ead.config.security.userdetails;

import com.ead.model.UserModel;
import com.ead.services.UserByIdOrElseThrowService;
import com.ead.services.UserByLoginOrElseThrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserByLoginOrElseThrowService userByLoginOrElseThrowService;
    private final UserByIdOrElseThrowService userByIdOrElseThrowService;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final UserModel userModel = this.userByLoginOrElseThrowService.call(login);

        return UserDetailsImpl.build(userModel);
    }

    public UserDetails loadUserById(final UUID userId) throws AuthenticationCredentialsNotFoundException {
        final UserModel userModel = this.userByIdOrElseThrowService.call(userId);

        return UserDetailsImpl.build(userModel);
    }
}
