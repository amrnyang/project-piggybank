package org.pb.config;

import lombok.Data;
import org.pb.model.PhoneNumber;
import org.pb.repository.UserRepository;
import org.pb.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Data
@Component
@Profile("local")
public class SampleDataPopulatorRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        final User user = User.builder().firstName("Rohit").lastName("Phular").email("r.p@test.com").build();

        final PhoneNumber phone1 = PhoneNumber.builder().countryCode(91).phone(11111111L).user(user).build();
        final PhoneNumber phone2 = PhoneNumber.builder().countryCode(44).phone(22222222L).user(user).build();

        final List<PhoneNumber> phoneNumbers = Arrays.asList(phone1, phone2);

        user.setPhoneNumber(phoneNumbers);

        userRepository.save(user);
    }
}
