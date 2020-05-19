package org.pb.service;

import lombok.Data;
import org.pb.model.LoginUserDetail;
import org.pb.model.UserAuth;
import org.pb.repository.UserAuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Data
@Service
public class LoginUserDetailService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final UserAuth dbUser = userAuthRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("User not found"));
        return new LoginUserDetail(dbUser);
    }
}
