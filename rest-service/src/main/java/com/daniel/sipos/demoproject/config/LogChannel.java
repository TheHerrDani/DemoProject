package com.daniel.sipos.demoproject.config;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;

@ServerEndpoint(value = "/channel/log")
@Slf4j
public class LogChannel {

  @OnMessage(maxMessageSize = 1) // MaxMessage 1 byte
  public void onMessage(String message) {
    log.debug("Recv Message: {}", message);
  }

}