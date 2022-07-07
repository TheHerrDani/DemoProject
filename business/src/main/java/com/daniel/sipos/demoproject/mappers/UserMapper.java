package com.daniel.sipos.demoproject.mappers;

import com.daniel.sipos.demoproject.domain.UserDomain;
import com.daniel.sipos.demoproject.entities.UserTable;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public UserDomain toUserDomain(UserTable userTable) {
    return UserDomain.builder()
        .id(userTable.getId())
        .emailAddress(userTable.getEmailAddress())
        .fullName(userTable.getFullName())
        .build();
  }

  public UserTable toUser(UserDomain userDomain) {
    return UserTable.builder()
        .id(userDomain.getId())
        .emailAddress(userDomain.getEmailAddress())
        .fullName(userDomain.getFullName())
        .build();
  }

}
