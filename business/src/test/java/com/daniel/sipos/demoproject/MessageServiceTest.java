package com.daniel.sipos.demoproject;


import static com.daniel.sipos.demoproject.BusinessTestConstants.EMIL_FEKETE_GMAIL_COM;
import static com.daniel.sipos.demoproject.BusinessTestConstants.SIPOS_HERR_DANI_GMAIL_COM;
import static com.daniel.sipos.demoproject.BusinessTestConstants.TEST_LOCAL_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;

import com.daniel.sipos.demoproject.domain.MessageDomain;
import com.daniel.sipos.demoproject.service.MessageService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = {SpringTestConfiguration.class})
@ActiveProfiles("test")
public class MessageServiceTest {
  @Autowired
  MessageService messageService;

  @Test
  public void findAllMessages() {
    List<MessageDomain> messages = messageService.findAllMessages();
    assertThat(messages).contains(getSpecificMessageDomain());
  }

  @Test
  public void findAllMessagesByUsers() {
    List<MessageDomain> messages =
        messageService.findAllMessagesByUsers(SIPOS_HERR_DANI_GMAIL_COM, EMIL_FEKETE_GMAIL_COM);
    assertThat(messages).contains(getSpecificMessageDomain());
  }

  @Test
  public void saveMessage() {
    MessageDomain createdMessage = getNewMessageDomain();
    int size = messageService.findAllMessages().size();
    MessageDomain savedMessage = messageService.saveMessage(createdMessage);
    assertThat(savedMessage.getMessage()).isEqualTo(("RANDOM"));
    assertThat(savedMessage.getFromUserEmail()).isEqualTo(SIPOS_HERR_DANI_GMAIL_COM);
    assertThat(savedMessage.getToUserEmail()).isEqualTo(EMIL_FEKETE_GMAIL_COM);
    assertThat(savedMessage.getInsertionDateTime()).isEqualTo(TEST_LOCAL_DATE_TIME);
  }

  private MessageDomain getSpecificMessageDomain() {
    return MessageDomain.builder()
        .id(1L)
        .message("Hello!")
        .fromUserEmail(SIPOS_HERR_DANI_GMAIL_COM)
        .toUserEmail(EMIL_FEKETE_GMAIL_COM)
        .insertionDateTime(TEST_LOCAL_DATE_TIME)
        .build();
  }

  private MessageDomain getNewMessageDomain() {
    return MessageDomain.builder()
        .id(1L)
        .message("RANDOM")
        .fromUserEmail(SIPOS_HERR_DANI_GMAIL_COM)
        .toUserEmail(EMIL_FEKETE_GMAIL_COM)
        .insertionDateTime(TEST_LOCAL_DATE_TIME)
        .build();
  }
}
