package com.bezkoder.springjwt.converter;

import com.bezkoder.springjwt.dto.UserDTO;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;

@Component
public class UserConverter {
    @Autowired
    RoleRepository roleRepository;
    public User toEntity(UserDTO dto, String pass){
        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setPassword(pass);
        entity.setFullName(dto.getFullName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setCreatedDate(new Date());
        return entity;
    }
    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        if(user.getId() != null){
            dto.setId(user.getId());
        }
        var usse = user.getRoles();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
//        var role = roleRepository.findAllById(user.getRoles());
        for(Role role : user.getRoles()){
            dto.setRole(role.getName().name());
        }
//        var role = roleRepository.getReferenceById(3L);
//        dto.setROLE_ADMIN(role.getName().name());
        dto.setFullName(user.getFullName());
        dto.setCreatedDate(user.getCreatedDate());
        dto.setModifiedDate(user.getModifiedDate());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        return dto;
    }
    public User toEntity(UserDTO dto,User user){
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());
        user.setModifiedDate(new Date());
        user.setEmail(dto.getEmail());
        return  user;
    }

}
