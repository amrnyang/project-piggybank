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

    /* Create a record */
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        if(applicationProperties.getAllowUserCreation()) {
            final User user = modelMapper.map(userDTO, User.class);
            userRepository.save(user);
            final UserDTO createdUserDTO = modelMapper.map(user, UserDTO.class);
            return new ResponseEntity<UserDTO>(createdUserDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    /* Fetch a record */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Integer id) {
        final User user = userRepository.findById(id).orElse(null);

        if(Objects.nonNull(user)) {
            final UserDTO existingUserDTO = modelMapper.map(user, UserDTO.class);
            return new ResponseEntity<UserDTO>(existingUserDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* Update a record */
    @PutMapping
    public ResponseEntity<UserDTO> get(@RequestBody UserDTO userDTO) {
        final User existingUser = userRepository.findById(userDTO.getId()).orElse(null);

        if(Objects.nonNull(existingUser)) {
            final User updatedUser = modelMapper.map(userDTO, User.class);
            userRepository.save(updatedUser);
            final UserDTO updatedUserDTO = modelMapper.map(updatedUser, UserDTO.class);
            return new ResponseEntity<UserDTO>(updatedUserDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* Delete a record */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
