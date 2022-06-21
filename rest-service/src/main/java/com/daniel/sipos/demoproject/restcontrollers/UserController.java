package com.daniel.sipos.demoproject.restcontrollers;

import com.daniel.sipos.demoproject.domain.UserDomain;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.daniel.sipos.demoproject.service.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping(path = "/find-all")
  public @ResponseBody
  List<UserDomain> findAllUsers() {
    return userService.findAllUsers();
  }

  @GetMapping(path = "/find-by-email")
  public @ResponseBody
  UserDomain findUserByEmail(@RequestParam String emailAddress) {
    return userService.findUserByEmail(emailAddress);
  }

  @PostMapping(path = "/save-if-not-exist")
  public @ResponseBody
  void saveUser(@RequestBody UserDomain user) {
    userService.saveUserIfNotExist(user);
  }
}
