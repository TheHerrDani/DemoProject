package com.daniel.sipos.demoproject;

import static com.daniel.sipos.demoproject.ControllerTestConstants.All_USER_JSON;
import static com.daniel.sipos.demoproject.ControllerTestConstants.NEW_EMAIL_ADDRESS;
import static com.daniel.sipos.demoproject.ControllerTestConstants.NEW_NAME;
import static com.daniel.sipos.demoproject.ControllerTestConstants.TEST_EMAIL_ADDRESS_ONE;
import static com.daniel.sipos.demoproject.ControllerTestConstants.TEST_USER_EMAIL;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.daniel.sipos.demoproject.domain.UserDomain;
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
public class UserTableControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @WithMockUser
  public void findAll() throws Exception {
    this.mockMvc.perform(get("/api/user/find-all"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(All_USER_JSON)));
  }

  @Test
  @WithMockUser
  public void findByEmail() throws Exception {
    this.mockMvc.perform(
            get("/api/user/find-by-email")
                .param("emailAddress", TEST_EMAIL_ADDRESS_ONE))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(TEST_USER_EMAIL)));
  }

  @Test
  @WithMockUser
  public void save() throws Exception {
    UserDomain userDomain = getUser();
    this.mockMvc.perform(
            post("/api/user/save-if-not-exist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDomain)))
        .andDo(print())
        .andExpect(status().isOk());
  }

  private UserDomain getUser() {
    return UserDomain.builder()
        .id(0L)
        .emailAddress(NEW_EMAIL_ADDRESS)
        .fullName(NEW_NAME)
        .build();
  }
}
