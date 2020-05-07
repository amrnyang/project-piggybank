package org.pb.resource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.pb.UserRepository;
import org.pb.config.ApplicationConfiguration;
import org.pb.dto.UserDTO;
import org.pb.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Data
@RestController
public class UserResource {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final ApplicationConfiguration applicationConfiguration;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) {
        log.info(applicationConfiguration.toString());
        final User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
