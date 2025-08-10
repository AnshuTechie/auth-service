package com.authentication.auth_service.repository;

import com.authentication.auth_service.model.Role;
import com.authentication.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity.
 * Provides built-in CRUD operations and custom query methods.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their email address.
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if a user exists with the given email.
     */
    boolean existsByEmail(String email);

    /**
     * Find all users with a specific role.
     */
    List<User> findByRole(Role role);
}







//package com.authentication.auth_service.repository;
//
//
//
//
//import com.authentication.auth_service.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByEmail(String email);
//}
//
//
