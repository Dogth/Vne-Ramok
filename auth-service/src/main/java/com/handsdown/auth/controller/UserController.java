package com.handsdown.auth.controller;

import com.handsdown.auth.model.User;
import com.handsdown.auth.service.UserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired private UserService userService;

  @RequestMapping(value = "/current", method = RequestMethod.GET)
  public Principal getUser(Principal principal) {
    return principal;
  }

  @PreAuthorize("#oauth2.hasScope('server')")
  @RequestMapping(method = RequestMethod.POST)
  public void createUser(@Valid @RequestBody User user) {
    userService.create(user);
  }
}
