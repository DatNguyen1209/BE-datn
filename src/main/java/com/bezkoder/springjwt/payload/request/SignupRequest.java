package com.bezkoder.springjwt.payload.request;

import com.bezkoder.springjwt.models.BaseEntities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest extends BaseEntities {
  @NotBlank
  private String username;
  @NotBlank
  private String password;
  @NotBlank
  private String fullName;
  @NotBlank
  private String email;
  @NotBlank
  private String address;
  @NotBlank
  private String phone;
  private Set<String> role;
}
