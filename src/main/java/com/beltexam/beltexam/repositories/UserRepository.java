package com.beltexam.beltexam.repositories;

import com.beltexam.beltexam.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAll();
//    Optional<User> findByEmail(String email);

}
