package com.handsdown.account.service;

import com.handsdown.account.model.Account;
import com.handsdown.account.model.User;

public interface AccountService {

  Account findByName(String accountName);

  Account create(User user);

  void saveChanges(String name, Account update);
}
