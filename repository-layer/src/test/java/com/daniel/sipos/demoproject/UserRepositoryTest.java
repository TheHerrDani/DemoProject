package com.daniel.sipos.demoproject;

import static com.daniel.sipos.demoproject.RepositoryTestConstants.FULL_NAMES;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.NEW_EMAIL_ADDRESS;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.NEW_NAME;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.TEST_EMAIL_ADDRESS_ONE;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.demoproject.entities.User;
import com.daniel.sipos.demoproject.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {SpringTestConfiguration.class})
@ActiveProfiles("test")
public class UserRepositoryTest {
  @Autowired
  UserRepository userRepository;

  @Test
  public void saveUser() {
    User createdUser = getUser();
    int size = userRepository.findUsers().size();
    User savedUser = userRepository.saveUser(createdUser);
    assertThat(savedUser.getId()).isEqualTo(size + 1);
    assertThat(savedUser.getFullName()).isEqualTo(NEW_NAME);
    assertThat(savedUser.getEmailAddress()).isEqualTo(NEW_EMAIL_ADDRESS);
  }

  @Test
  public void getUserByEmail() {
    User user = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    assertThat(user.getId()).isEqualTo(1);
    assertThat(user.getFullName()).isEqualTo("Sipos Daniel");
  }

  @Test
  public void getUsers() {
    List<User> users = userRepository.findUsers();
    List<String> fullNames = users.stream().map(User::getFullName).collect(Collectors.toList());
    assertThat(fullNames).contains(FULL_NAMES);
  }

  private User getUser() {
    return User.builder()
        .id(0L)
        .emailAddress(NEW_EMAIL_ADDRESS)
        .fullName(NEW_NAME)
        .build();
  }
}

