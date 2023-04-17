package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



  @Query(nativeQuery = true , value = " select u.full_name as fullName, u.password as password, u.username as username, r.name  as role"+

          " from user_roles ur " +
          " inner join users u on ur.user_id = u.id " +
          " inner join roles r on ur.role_id = r.id " +
          " where ur.role_id = 3 ")
  Page<UserDTO.UserViewDto> getListUserByRoleAdmin(Pageable pageable);
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

}
