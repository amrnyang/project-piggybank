package org.pb.config;

import lombok.Data;
import org.apache.catalina.User;
import org.pb.model.UserAuth;
import org.pb.repository.UserAuthRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Data
@Component
public class LocalDataPopulator implements CommandLineRunner {

    private final UserAuthRepository userAuthRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userAuthRepository.deleteAll();;

        UserAuth normalUser = UserAuth.builder()
                .userName("bruce")
                .password(passwordEncoder.encode("pass"))
                .roles("ROLE_USER")
                .active(true)
                .build();

        UserAuth adminUser = UserAuth.builder()
                .userName("david")
                .password(passwordEncoder.encode("pass"))
                .roles("ROLE_ADMIN")
                .active(true)
                .build();

        UserAuth inactiveUser = UserAuth.builder()
                .userName("john")
                .password(passwordEncoder.encode("pass"))
                .roles("ROLE_ADMIN")
                .active(false)
                .build();

        userAuthRepository.saveAll(Arrays.asList(normalUser, adminUser, inactiveUser));
    }
}
