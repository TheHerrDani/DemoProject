package com.daniel.sipos.demoproject;

import static com.daniel.sipos.demoproject.RepositoryTestConstants.TEST_EMAIL_ADDRESS_ONE;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.TEST_EMAIL_ADDRESS_TWO;
import static com.daniel.sipos.demoproject.RepositoryTestConstants.TEST_LOCAL_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.demoproject.entities.Message;
import com.daniel.sipos.demoproject.entities.UserTable;
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
    UserTable fromUserTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    UserTable toUserTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_TWO).orElseThrow();
    Message createdMessage = getMessage(fromUserTable, toUserTable);
    Message savedMessage = messageRepository.saveMessage(createdMessage);
    assertThat(savedMessage.getMessage()).isEqualTo("Test");
    assertThat(savedMessage.getFromUserTable()).isEqualTo(fromUserTable);
    assertThat(savedMessage.getToUserTable()).isEqualTo(toUserTable);
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
    UserTable fromUserTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    UserTable toUserTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_TWO).orElseThrow();
    Message message =
        messageRepository.findMessageByInsertionTime(TEST_LOCAL_DATE_TIME).orElseThrow();
    assertThat(message.getId()).isEqualTo(1);
    assertThat(message.getMessage()).isEqualTo("Hello!");
    assertThat(message.getFromUserTable()).isEqualTo(fromUserTable);
    assertThat(message.getToUserTable()).isEqualTo(toUserTable);
  }

  private Message getMessage(UserTable fromUserTable, UserTable toUserTable) {
    return Message.builder()
        .id(0L)
        .message("Test")
        .fromUserTable(fromUserTable)
        .toUserTable(toUserTable)
        .insertionDateTime(LocalDateTime.now())
        .build();
  }

  private Message alreadyPersistedFirstMessage() {
    UserTable fromUserTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    UserTable toUserTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_TWO).orElseThrow();
    return Message.builder()
        .id(1L)
        .message("Hello!")
        .fromUserTable(fromUserTable)
        .toUserTable(toUserTable)
        .insertionDateTime(TEST_LOCAL_DATE_TIME)
        .build();
  }

  private Message alreadyPersistedLastMessage() {
    UserTable fromUserTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    UserTable toUserTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    return Message.builder()
        .id(5L)
        .message("Bye Bye")
        .fromUserTable(fromUserTable)
        .toUserTable(toUserTable)
        .insertionDateTime(TEST_LOCAL_DATE_TIME)
        .build();
  }

  private Message alreadyPersistedResponseMessage() {
    UserTable fromUserTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_TWO).orElseThrow();
    UserTable toUserTable = userRepository.findUserByEmail(TEST_EMAIL_ADDRESS_ONE).orElseThrow();
    return Message.builder()
        .id(2L)
        .message("Hy")
        .fromUserTable(fromUserTable)
        .toUserTable(toUserTable)
        .insertionDateTime(TEST_LOCAL_DATE_TIME)
        .build();
  }
}
