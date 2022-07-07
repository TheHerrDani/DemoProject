package com.daniel.sipos.demoproject.mappers;

import com.daniel.sipos.demoproject.domain.MessageDomain;
import com.daniel.sipos.demoproject.entities.Message;
import com.daniel.sipos.demoproject.entities.UserTable;
import com.daniel.sipos.demoproject.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

  @Autowired
  UserRepository userRepository;

  public MessageDomain toMessageDomain(Message message) {
    return MessageDomain.builder()
        .id(message.getId())
        .message(message.getMessage())
        .toUserEmail(message.getToUserTable().getEmailAddress())
        .fromUserEmail(message.getFromUserTable().getEmailAddress())
        .insertionDateTime(message.getInsertionDateTime())
        .build();
  }

  public Message toMessage(MessageDomain messageDomain) {
    Optional<UserTable> fromUser = userRepository.findUserByEmail(messageDomain.getFromUserEmail());
    Optional<UserTable> toUser = userRepository.findUserByEmail(messageDomain.getToUserEmail());
    return Message.builder()
        .id(messageDomain.getId())
        .message(messageDomain.getMessage())
        .toUserTable(toUser.orElseThrow())
        .fromUserTable(fromUser.orElseThrow())
        .insertionDateTime(messageDomain.getInsertionDateTime())
        .build();
  }
}
