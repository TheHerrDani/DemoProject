package com.daniel.sipos.demoproject.mappers;

import com.daniel.sipos.demoproject.domain.MessageDomain;
import com.daniel.sipos.demoproject.entities.Message;
import com.daniel.sipos.demoproject.entities.User;
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
        .toUserEmail(message.getToUser().getEmailAddress())
        .fromUserEmail(message.getFromUser().getEmailAddress())
        .insertionDateTime(message.getInsertionDateTime())
        .build();
  }

  public Message toMessage(MessageDomain messageDomain) {
    Optional<User> fromUser = userRepository.findUserByEmail(messageDomain.getFromUserEmail());
    Optional<User> toUser = userRepository.findUserByEmail(messageDomain.getToUserEmail());
    return Message.builder()
        .id(messageDomain.getId())
        .message(messageDomain.getMessage())
        .toUser(toUser.orElseThrow())
        .fromUser(fromUser.orElseThrow())
        .insertionDateTime(messageDomain.getInsertionDateTime())
        .build();
  }
}
