package com.daniel.sipos.demoproject;


import static com.daniel.sipos.demoproject.ControllerTestConstants.All_MESSAGE_JSON;
import static com.daniel.sipos.demoproject.ControllerTestConstants.EMIL_FEKETE_GMAIL_COM;
import static com.daniel.sipos.demoproject.ControllerTestConstants.MESSAGE_BY_USERS_JSON;
import static com.daniel.sipos.demoproject.ControllerTestConstants.SIPOS_HERR_DANI_GMAIL_COM;
import static com.daniel.sipos.demoproject.ControllerTestConstants.TEST_LOCAL_DATE_TIME;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.daniel.sipos.demoproject.domain.MessageDomain;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = {SpringTestConfiguration.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class MessageControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @WithMockUser
  public void findAll() throws Exception {
    this.mockMvc.perform(get("/api/message/find-all"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(All_MESSAGE_JSON)));
  }

  @Test
  @WithMockUser
  public void findAllByUsers() throws Exception {
    this.mockMvc.perform(
            get("/api/message/find-all-by-users")
                .param("fromEmail", SIPOS_HERR_DANI_GMAIL_COM)
                .param("toEmail", EMIL_FEKETE_GMAIL_COM))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(MESSAGE_BY_USERS_JSON)));
  }

  @Test
  @WithMockUser
  public void save() throws Exception {
    MessageDomain messageDomain = getMessageDomain();
    this.mockMvc.perform(
            post("/api/message/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(messageDomain)))
        .andDo(print())
        .andExpect(status().isOk());
  }

  private MessageDomain getMessageDomain() {
    return MessageDomain.builder()
        .id(0L)
        .message("Test")
        .fromUserEmail(SIPOS_HERR_DANI_GMAIL_COM)
        .toUserEmail(EMIL_FEKETE_GMAIL_COM)
        .insertionDateTime(TEST_LOCAL_DATE_TIME)
        .build();
  }
}