package org.pb.resource;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.pb.config.ApplicationProperties;
import org.pb.dto.UserDTO;
import org.pb.model.User;
import org.pb.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Data
@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final ApplicationProperties applicationProperties;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) {
        if(applicationProperties.getAllowUserCreation()) {
            final User user = modelMapper.map(userDTO, User.class);
            userRepository.save(user);
            // TODO: map model back to DTO before returning
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        final User user = userRepository.findById(id).orElse(null);

        if(Objects.nonNull(user)) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
