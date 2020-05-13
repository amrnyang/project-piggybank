package org.pb.resource;

import lombok.Data;
import org.pb.config.ApplicationProperties;
import org.pb.dto.UserDTO;
import org.pb.mapper.IUserMapper;
import org.pb.model.User;
import org.pb.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Data
@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserRepository userRepository;

    private final IUserMapper userMapper;

    private final ApplicationProperties applicationProperties;

    private final EntityManager entityManager;

    /* Create a record */
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        if(applicationProperties.getAllowUserCreation()) {
            final User user = userMapper.toEntity(userDTO);
            userRepository.save(user);
            final UserDTO createdUserDTO = userMapper.toDTO(user);
            return new ResponseEntity<UserDTO>(createdUserDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    /* Fetch a record */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Integer id) {
        final User user = userRepository.findById(id).orElse(null);

        if(Objects.nonNull(user)) {
            final UserDTO existingUserDTO = userMapper.toDTO(user);
            return new ResponseEntity<>(existingUserDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /* Fetch a record */
    @GetMapping("/record/{lastName}")
    public ResponseEntity<UserDTO> getByLastName(@PathVariable String lastName) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

        final Root<User> from = query.from(User.class);
        final Predicate lastNamePredicate = criteriaBuilder.equal(from.get("lastName"), lastName);

        query.where(lastNamePredicate);

        final TypedQuery<User> dbQuery = entityManager.createQuery(query);

        final List<User> resultList = dbQuery.getResultList();

        if(Objects.nonNull(resultList) && resultList.size() > 0) {
            final UserDTO existingUserDTO = userMapper.toDTO(resultList.stream().findFirst().get());
            return new ResponseEntity<>(existingUserDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/record/fullname/{id}")
    public ResponseEntity<String> getFullName(@PathVariable Integer id) {

        final String fullName = userRepository.findFullName(id);

        System.out.println(userRepository.getUserByNativeQuery("Phular"));
        System.out.println(userRepository.getUserByEntityQuery("Phular"));
        System.out.println(userRepository.findByLastName("Phular"));

        return new ResponseEntity<>(fullName, HttpStatus.OK);
    }

    /* Update a record */
//    @PutMapping
//    public ResponseEntity<UserDTO> get(@RequestBody UserDTO userDTO) {
//        final User existingUser = userRepository.findById(userDTO.getId()).orElse(null);
//
//        if(Objects.nonNull(existingUser)) {
//            final User updatedUser = modelMapper.map(userDTO, User.class);
//            userRepository.save(updatedUser);
//            final UserDTO updatedUserDTO = modelMapper.map(updatedUser, UserDTO.class);
//            return new ResponseEntity<UserDTO>(updatedUserDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    /* Delete a record */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
