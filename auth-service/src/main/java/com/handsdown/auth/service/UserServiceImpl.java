package com.handsdown.auth.service;

import com.handsdown.auth.model.User;
import com.handsdown.auth.repository.UserRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Autowired private UserRepository repository;

  @Override
  public void create(User user) {

    Optional<User> existing = repository.findById(user.getUsername());
    existing.ifPresent(
        it -> {
          throw new IllegalArgumentException("user already exists: " + it.getUsername());
        });

    String hash = encoder.encode(user.getPassword());
    user.setPassword(hash);

    repository.save(user);

    log.info("new user has been created: {}", user.getUsername());
  }
}
