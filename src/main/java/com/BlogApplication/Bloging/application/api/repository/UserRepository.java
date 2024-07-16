package com.BlogApplication.Bloging.application.api.repository;

import com.BlogApplication.Bloging.application.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  @Query(value = "SELECT * FROM USER u WHERE u.username=?1",nativeQuery = true)
  Optional<User> findUserByUserName(String username);
}
