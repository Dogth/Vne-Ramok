package com.handsdown.account.service;

import com.handsdown.account.client.AuthServiceClient;
import com.handsdown.account.client.StatisticsServiceClient;
import com.handsdown.account.domain.Account;
import com.handsdown.account.domain.User;
import com.handsdown.account.repository.AccountRepository;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AccountServiceImpl implements AccountService {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired private StatisticsServiceClient statisticsClient;

  @Autowired private AuthServiceClient authClient;

  @Autowired private AccountRepository repository;

  @Override
  public Account findByName(String accountName) {
    Assert.hasLength(accountName);
    return repository.findByName(accountName);
  }

  @Override
  public Account create(User user) {

    Account existing = repository.findByName(user.getUsername());
    Assert.isNull(existing, "account already exists: " + user.getUsername());

    authClient.createUser(user);

    Account account = new Account();
    account.setName(user.getUsername());
    account.setLastSeen(new Date());

    repository.save(account);

    log.info("new account has been created: " + account.getName());

    return account;
  }

  @Override
  public void saveChanges(String name, Account update) {

    Account account = repository.findByName(name);
    Assert.notNull(account, "can't find account with name " + name);

    account.setNote(update.getNote());
    account.setLastSeen(new Date());
    repository.save(account);

    log.debug("account {} changes has been saved", name);

    statisticsClient.updateStatistics(name, account);
  }
}
