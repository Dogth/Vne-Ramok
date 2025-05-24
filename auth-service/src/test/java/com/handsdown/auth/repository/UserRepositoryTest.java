package com.handsdown.auth.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.handsdown.auth.model.User;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

  @Autowired private UserRepository repository;

  @Test
  public void shouldSaveAndFindUserByName() {

    User user = new User();
    user.setUsername("name");
    user.setPassword("password");
    repository.save(user);

    Optional<User> found = repository.findById(user.getUsername());
    assertTrue(found.isPresent());
    assertEquals(user.getUsername(), found.get().getUsername());
    assertEquals(user.getPassword(), found.get().getPassword());
  }
}
