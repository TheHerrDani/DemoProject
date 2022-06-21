package com.daniel.sipos.demoproject.restcontrollers;

import com.daniel.sipos.demoproject.domain.MessageDomain;
import com.daniel.sipos.demoproject.service.MessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/message")
public class MessageController {
  public static final String SENDER_GET_MESSAGE = "/sender/get-message/";

  @Autowired
  MessageService messageService;

  @Autowired
  private SimpMessagingTemplate simpleMessagingTemplate;

  @GetMapping(path = "/find-all")
  public @ResponseBody
  List<MessageDomain> findMessages() {
    return messageService.findAllMessages();
  }

  @GetMapping(path = "/find-all-by-users")
  public @ResponseBody
  List<MessageDomain> findMessages(@RequestParam String fromEmail,
                                   @RequestParam String toEmail) {
    return messageService.findAllMessagesByUsers(fromEmail, toEmail);
  }

  @PostMapping(path = "/save")
  @MessageMapping("/send-message")
  public void greeting(@RequestBody MessageDomain message) {
    MessageDomain messageDomain = messageService.saveMessage(message);
    String fromAddress = SENDER_GET_MESSAGE + message.getFromUserEmail();
    String toAddress = SENDER_GET_MESSAGE + message.getToUserEmail();
    String adminAddress = SENDER_GET_MESSAGE + "admin";
    simpleMessagingTemplate.convertAndSend(fromAddress, messageDomain);
    if (!fromAddress.equals(toAddress)) {
      simpleMessagingTemplate.convertAndSend(toAddress, messageDomain);
    }
    simpleMessagingTemplate.convertAndSend(adminAddress, messageDomain);
  }
}
