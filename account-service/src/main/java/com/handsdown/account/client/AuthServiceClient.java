package com.handsdown.account.client;

import com.handsdown.account.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

  @RequestMapping(
      method = RequestMethod.POST,
      value = "/uaa/users",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  void createUser(User user);
}
