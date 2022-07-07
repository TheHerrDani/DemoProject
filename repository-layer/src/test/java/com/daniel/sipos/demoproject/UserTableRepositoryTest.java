package com.daniel.sipos.demoproject;

import static com.daniel.sipos.demoproject.RepositoryTestConstants.FULL_NAMES;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.NEW_EMAIL_ADDRESS;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.NEW_NAME;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.TEST_EMAIL_ADDRESS_ONE;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.demoproject.entities.UserTable;
import com.daniel.sipos.demoproject.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {SpringTestConfiguration.class})
@ActiveProfiles("test")
public class UserTableRepositoryTest {
  @Autowired
  UserRepository userRepository;

  @Test
  public void saveUser() {
    UserTable createdUserTable = getUser();
    int size = userRepository.findUsers().size();
    UserTable savedUserTable = userRepository.saveUser(createdUserTable);
    assertThat(savedUserTable.getId()).isEqualTo(size + 1);
    assertThat(savedUserTable.getFullName()).isEqualTo(NEW_NAME);
    assertThat(savedUserTable.getEmailAddress()).isEqualTo(NEW_EMAIL_ADDRESS);
  }

  @Test
  public void getUserByEmail() {
    UserTable userTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    assertThat(userTable.getId()).isEqualTo(1);
    assertThat(userTable.getFullName()).isEqualTo(TEST_NAME);
  }

  @Test
  public void getUsers() {
    List<UserTable> userTables = userRepository.findUsers();
    List<String> fullNames = userTables.stream().map(UserTable::getFullName).collect(Collectors.toList());
    assertThat(fullNames).contains(FULL_NAMES);
  }

  private UserTable getUser() {
    return UserTable.builder()
        .id(0L)
        .emailAddress(NEW_EMAIL_ADDRESS)
        .fullName(NEW_NAME)
        .build();
  }
}

