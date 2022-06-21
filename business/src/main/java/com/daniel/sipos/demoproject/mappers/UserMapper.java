package com.daniel.sipos.demoproject.mappers;

import com.daniel.sipos.demoproject.domain.UserDomain;
import com.daniel.sipos.demoproject.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public UserDomain toUserDomain(User user) {
    return UserDomain.builder()
        .id(user.getId())
        .emailAddress(user.getEmailAddress())
        .fullName(user.getFullName())
        .build();
  }

  public User toUser(UserDomain userDomain) {
    return User.builder()
        .id(userDomain.getId())
        .emailAddress(userDomain.getEmailAddress())
        .fullName(userDomain.getFullName())
        .build();
  }

}
