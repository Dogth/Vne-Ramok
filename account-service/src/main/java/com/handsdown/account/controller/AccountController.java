package com.handsdown.account.controller;

import com.handsdown.account.model.Account;
import com.handsdown.account.model.User;
import com.handsdown.account.service.AccountService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

  @Autowired private AccountService accountService;

  @PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
  @RequestMapping(path = "/{name}", method = RequestMethod.GET)
  public Account getAccountByName(@PathVariable String name) {
    return accountService.findByName(name);
  }

  @RequestMapping(path = "/current", method = RequestMethod.GET)
  public Account getCurrentAccount(Principal principal) {
    return accountService.findByName(principal.getName());
  }

  @RequestMapping(path = "/current", method = RequestMethod.PUT)
  public void saveCurrentAccount(Principal principal, @Valid @RequestBody Account account) {
    accountService.saveChanges(principal.getName(), account);
  }

  @RequestMapping(path = "/", method = RequestMethod.POST)
  public Account createNewAccount(@Valid @RequestBody User user) {
    return accountService.create(user);
  }
}
