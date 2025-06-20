package com.handsdown.account.model;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Data
public class User {

  @NotNull
  @Length(min = 3, max = 20)
  private String username;

  @NotNull
  @Length(min = 6, max = 40)
  private String password;
}
