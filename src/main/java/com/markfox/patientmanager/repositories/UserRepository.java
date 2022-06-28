package com.markfox.patientmanager.repositories;

import com.markfox.patientmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// User Entity Repository
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Method used during Authentication
    Optional<User> findByEmail(String email);

    // Custom query to update a User's password for times when they forgot it
    @Modifying
    @Transactional
    @Query("update User u set u.password = :newPassword where u.email = :userEmail")
    void updateUserPasswordByEmail(@Param("newPassword") String password, @Param("userEmail") String email);
}
