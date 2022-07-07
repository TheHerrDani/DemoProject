package com.daniel.sipos.demoproject;

import static com.daniel.sipos.demoproject.ControllerTestConstants.All_USER_JSON;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = {SpringTestConfiguration.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VersionControllerTest {

  public static final String VERSION = "2.7.0";
  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser
  public void find() throws Exception {
    this.mockMvc.perform(get("/api/version/find"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(VERSION)));
  }
}
