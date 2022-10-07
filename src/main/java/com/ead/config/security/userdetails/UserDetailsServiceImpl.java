package com.ead.config.security.userdetails;

import com.ead.model.UserModel;
import com.ead.services.UserByLoginOrElseThrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserByLoginOrElseThrowService userByLoginOrElseThrowService;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final UserModel userModel = this.userByLoginOrElseThrowService.call(login);

        return UserDetailsImpl.build(userModel);
    }
}
