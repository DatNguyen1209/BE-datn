package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.BaseEntities;
import com.bezkoder.springjwt.models.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseEntities {
    private  String username;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private String role;

    public interface UserViewDto{
        String getUsername();
        String getPassword();
        String getFullName();
        String getRole();
    }
}
