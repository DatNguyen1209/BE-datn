package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.converter.UserConverter;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.dto.UserDTO;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverter converter;
    @Autowired
    IUserService iUserService;
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(()->
                new RuntimeException("Not found user have id!!"));
    }
    @CrossOrigin
    @GetMapping("/getAllUser")
    public PageDTO<UserDTO> getAllUser(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        return iUserService.findAllWithPageable(page,size);
    }

    @CrossOrigin
    @GetMapping("/getAllUserByRoleName")
    public ResponseEntity<?> getAllUserByRoleAdmin(@RequestParam("page") int page, @RequestParam("size") int size
    ){
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            var userList = userRepository.getListUserByRoleAdmin(pageable);

            var a = userRepository.getReferenceById(1L);
//        return PageDTO.of(userList, userList.stream().map(user -> converter.toDTO(user)).collect(Collectors.toList()));
            return userList != null
                    ? ResponseEntity.ok(userList)
                    : ResponseEntity.badRequest().body(userList);
//        var uL = userRepository.getListUserByRoleAdmin();
//        return  uL.size() > 0
//                ? ResponseEntity.ok(uL)
//                : ResponseEntity.badRequest().body(uL);
        } catch (Exception ex ){
            log.error("ádfsdfsdfsdf"+ ex);
            return null;
        }
    }
    @CrossOrigin
    @PutMapping("/update/{id}")
    public  UserDTO updateUser(@RequestBody UserDTO dto,@PathVariable("id") Long id){
        dto.setId(id);
        return iUserService.update(dto);
    }

}
