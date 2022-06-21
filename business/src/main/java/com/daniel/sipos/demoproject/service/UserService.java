package com.daniel.sipos.demoproject.service;

import com.daniel.sipos.demoproject.domain.UserDomain;
import com.daniel.sipos.demoproject.entities.User;
import com.daniel.sipos.demoproject.mappers.UserMapper;
import com.daniel.sipos.demoproject.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  public List<UserDomain> findAllUsers() {
    return userRepository
        .findUsers()
        .stream()
        .map(x -> userMapper.toUserDomain(x))
        .collect(Collectors.toList());
  }

  public UserDomain findUserByEmail(String emailAddress) {
    Optional<User> userByEmail = userRepository.findUserByEmail(emailAddress);
    return userMapper.toUserDomain(userByEmail.orElseThrow());
  }

  public void saveUserIfNotExist(UserDomain userDomain) {
    Optional<User> savedUser = userRepository.findUserByEmail(userDomain.getEmailAddress());
    if (savedUser.isEmpty()) {
      userRepository.saveUser(userMapper.toUser(userDomain));
    }
    findAllUsers();
  }
}

