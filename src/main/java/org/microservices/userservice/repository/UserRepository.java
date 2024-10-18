package org.microservices.userservice.repository;

import org.microservices.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    // custom query methods can be added here
}
