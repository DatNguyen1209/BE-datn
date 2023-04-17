package com.bezkoder.springjwt.serviceimpl;

import com.bezkoder.springjwt.converter.UserConverter;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.dto.UserDTO;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserConverter converter;
    @Autowired
    UserRepository userRepository;
    @Override
    public PageDTO<UserDTO> findAllWithPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<User> userList = userRepository.findAll(pageable);

        return PageDTO.of(userList, userList.stream().map(user -> converter.toDTO(user)).collect(Collectors.toList()));
    }

    @Override
    public UserDTO update(UserDTO dto) {
        User entity = new User();
        if(dto.getId() != null){
            Optional<User> user = userRepository.findById(dto.getId());
            entity = converter.toEntity(dto,user.get());
        }
        entity = userRepository.save(entity);
        return converter.toDTO(entity);
    }


}
