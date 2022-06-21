package com.daniel.sipos.demoproject;

import static com.daniel.sipos.demoproject.RepositoryTestConstants.TEST_EMAIL_ADDRESS_ONE;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.TEST_EMAIL_ADDRESS_TWO;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.TEST_LOCAL_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.demoproject.entities.Message;
import com.daniel.sipos.demoproject.entities.User;
import com.daniel.sipos.demoproject.repositories.MessageRepository;
import com.daniel.sipos.demoproject.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {SpringTestConfiguration.class})
@ActiveProfiles("test")
public class MessageRepositoryTest {

  @Autowired
  MessageRepository messageRepository;

  @Autowired
  UserRepository userRepository;

  @Test
  public void saveMessage() {
    User fromUser = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    User toUser = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_TWO).orElseThrow();
    Message createdMessage = getMessage(fromUser, toUser);
    Message savedMessage = messageRepository.saveMessage(createdMessage);
    assertThat(savedMessage.getMessage()).isEqualTo("Test");
    assertThat(savedMessage.getFromUser()).isEqualTo(fromUser);
    assertThat(savedMessage.getToUser()).isEqualTo(toUser);
  }

  @Test
  public void getMessageByUsers() {
    List<Message> userMessages =
        messageRepository.findAllMessagesByUsers(TEST_EMAIL_ADDRESS_ONE, TEST_EMAIL_ADDRESS_TWO);
    assertThat(userMessages).contains(alreadyPersistedFirstMessage(),
        alreadyPersistedResponseMessage());
  }

  @Test
  public void getMessages() {
    List<Message> userMessages = messageRepository.findAllMessages();
    assertThat(userMessages).contains(alreadyPersistedFirstMessage(),
        alreadyPersistedLastMessage());
  }

  @Test
  public void getMessageByInsertionTime() {
    User fromUser = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    User toUser = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_TWO).orElseThrow();
    Message message =
        messageRepository.findMessageByInsertionTime(TEST_LOCAL_DATE_TIME).orElseThrow();
    assertThat(message.getId()).isEqualTo(1);
    assertThat(message.getMessage()).isEqualTo("Hello!");
    assertThat(message.getFromUser()).isEqualTo(fromUser);
    assertThat(message.getToUser()).isEqualTo(toUser);
  }

  private Message getMessage(User fromUser, User toUser) {
    return Message.builder()
        .id(0L)
        .message("Test")
        .fromUser(fromUser)
        .toUser(toUser)
        .insertionDateTime(LocalDateTime.now())
        .build();
  }

  private Message alreadyPersistedFirstMessage() {
    User fromUser = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    User toUser = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_TWO).orElseThrow();
    return Message.builder()
        .id(1L)
        .message("Hello!")
        .fromUser(fromUser)
        .toUser(toUser)
        .insertionDateTime(TEST_LOCAL_DATE_TIME)
        .build();
  }

  private Message alreadyPersistedLastMessage() {
    User fromUser = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    User toUser = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    return Message.builder()
        .id(5L)
        .message("Bye Bye")
        .fromUser(fromUser)
        .toUser(toUser)
        .insertionDateTime(TEST_LOCAL_DATE_TIME)
        .build();
  }

  private Message alreadyPersistedResponseMessage() {
    User fromUser = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_TWO).orElseThrow();
    User toUser = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    return Message.builder()
        .id(2L)
        .message("Hy")
        .fromUser(fromUser)
        .toUser(toUser)
        .insertionDateTime(TEST_LOCAL_DATE_TIME)
        .build();
  }
}
