package com.codingbydivyanshu.yt.HospitalManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codingbydivyanshu.yt.HospitalManagement.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findByUsername(String username);

}
