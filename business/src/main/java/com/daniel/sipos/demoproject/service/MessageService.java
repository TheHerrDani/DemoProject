package com.daniel.sipos.demoproject.service;


import com.daniel.sipos.demoproject.domain.MessageDomain;
import com.daniel.sipos.demoproject.entities.Message;
import com.daniel.sipos.demoproject.mappers.MessageMapper;
import com.daniel.sipos.demoproject.repositories.MessageRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private MessageMapper messageMapper;

  public List<MessageDomain> findAllMessages() {
    return messageRepository
        .findAllMessages()
        .stream()
        .map(x -> messageMapper.toMessageDomain(x))
        .collect(Collectors.toList());
  }

  public List<MessageDomain> findAllMessagesByUsers(String fromEmail, String toEmail) {
    return messageRepository
        .findAllMessagesByUsers(fromEmail, toEmail)
        .stream()
        .map(x -> messageMapper.toMessageDomain(x))
        .collect(Collectors.toList());
  }

  public MessageDomain saveMessage(
      MessageDomain messageDomain) {
    Message message = messageRepository.saveMessage(messageMapper.toMessage(messageDomain));
    return messageMapper.toMessageDomain(message);
  }

}
