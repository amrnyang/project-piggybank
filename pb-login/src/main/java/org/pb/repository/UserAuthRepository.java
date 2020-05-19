package org.pb.repository;

import org.pb.model.UserAuth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends CrudRepository<UserAuth, Integer> {

    Optional<UserAuth> findByUserName(String userName);
}
