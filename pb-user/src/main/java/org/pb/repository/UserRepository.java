package org.pb.repository;

import org.pb.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Procedure("FIND_FULLNAME")
    String findFullName(Integer id);

    @Query(value = "select * from user u where u.last_name =:lastName", nativeQuery = true)
    User getUserByNativeQuery(@Param("lastName") String lastName);

    @Query(value = "select u from User u where u.lastName =:lastName")
    User getUserByEntityQuery(@Param("lastName") String lastName);

    User findByLastName(String lastName);
}
