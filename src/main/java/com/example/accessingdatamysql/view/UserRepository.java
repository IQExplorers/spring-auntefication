package com.example.accessingdatamysql.view;

import com.example.accessingdatamysql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByNameAndPassword(String username, String password);
}
