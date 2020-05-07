package org.pb.config;

import lombok.Data;
import org.pb.repository.UserRepository;
import org.pb.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Data
@Component
@Profile("!local")
public class SampleDataPopulatorRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("Rohit");
        user.setLastName("Phular");

        userRepository.save(user);
    }
}
