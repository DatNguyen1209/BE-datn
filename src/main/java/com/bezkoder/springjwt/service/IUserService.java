package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.dto.UserDTO;
import com.bezkoder.springjwt.repository.UserRepository;

public interface IUserService {
    PageDTO<UserDTO> findAllWithPageable(int page, int size);
    UserDTO update(UserDTO dto);
}
