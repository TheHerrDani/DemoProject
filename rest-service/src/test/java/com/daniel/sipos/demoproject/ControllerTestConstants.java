package com.daniel.sipos.demoproject;

import java.time.LocalDateTime;
import java.time.Month;

public class ControllerTestConstants {
  public static final String All_MESSAGE_JSON =
      "[{\"id\":1,\"fromUserEmail\":\"sipos.herr.dani@gmail.com\"," +
          "\"toUserEmail\":\"emil.fekete@gmail.com\",\"message\":\"Hello!\"," +
          "\"insertionDateTime\":\"2022-05-28T13:14:15\"},{\"id\":2," +
          "\"fromUserEmail\":\"emil.fekete@gmail.com\"," +
          "\"toUserEmail\":\"sipos.herr.dani@gmail.com\"," +
          "\"message\":\"Hy\",\"insertionDateTime\":\"2022-05-28T13:15:15\"},{\"id\":3," +
          "\"fromUserEmail\":\"sipos.herr.dani@gmail.com\"," +
          "\"toUserEmail\":\"emil.fekete@gmail.com\",\"message\":\"Whats up?\"," +
          "\"insertionDateTime\":\"2022-05-28T13:16:15\"},{\"id\":4," +
          "\"fromUserEmail\":\"sipos.herr.dani@gmail.com\",\"toUserEmail\":\"krajlanora@gmail.com\"," +
          "\"message\":\"Buy some cat food pls\",\"insertionDateTime\":\"2022-05-28T13:17:15\"}," +
          "{\"id\":5,\"fromUserEmail\":\"krajlanora@gmail.com\"," +
          "\"toUserEmail\":\"sipos.herr.dani@gmail.com\"," +
          "\"message\":\"Ok\",\"insertionDateTime\":\"2022-05-28T13:17:20\"}," +
          "{\"id\":6,\"fromUserEmail\":\"sipos.herr.dani@gmail.com\"," +
          "\"toUserEmail\":\"sipos.herr.dani@gmail.com\",\"message\":\"Bye Bye\"," +
          "\"insertionDateTime\":\"2022-05-28T13:17:20\"}]";

  public static final String MESSAGE_BY_USERS_JSON =
      "[{\"id\":1,\"fromUserEmail\":\"sipos.herr.dani@gmail.com\"," +
          "\"toUserEmail\":\"emil.fekete@gmail.com\",\"message\":\"Hello!\"," +
          "\"insertionDateTime\":\"2022-05-28T13:14:15\"},{\"id\":2," +
          "\"fromUserEmail\":\"emil.fekete@gmail.com\"," +
          "\"toUserEmail\":\"sipos.herr.dani@gmail.com\",\"message\":\"Hy\"," +
          "\"insertionDateTime\":\"2022-05-28T13:15:15\"},{\"id\":3," +
          "\"fromUserEmail\":\"sipos.herr.dani@gmail.com\"," +
          "\"toUserEmail\":\"emil.fekete@gmail.com\",\"message\":\"Whats up?\"," +
          "\"insertionDateTime\":\"2022-05-28T13:16:15\"}]";

  public static final String All_USER_JSON =
      "[{\"id\":1,\"emailAddress\":\"sipos.herr.dani@gmail.com\",\"fullName\":\"Sipos Daniel\"}," +
          "{\"id\":2,\"emailAddress\":\"emil.fekete@gmail.com\",\"fullName\":\"Fekete Emil\"}," +
          "{\"id\":3,\"emailAddress\":\"krajlanora@gmail.com\",\"fullName\":\"Krajla Nora\"}]";

  public static final String TEST_USER_EMAIL =
      "{\"id\":1,\"emailAddress\":\"sipos.herr.dani@gmail.com\",\"fullName\":\"Sipos Daniel\"}";


  public static final String SIPOS_HERR_DANI_GMAIL_COM = "sipos.herr.dani@gmail.com";
  public static final String EMIL_FEKETE_GMAIL_COM = "emil.fekete@gmail.com";
  public static final LocalDateTime TEST_LOCAL_DATE_TIME =
      LocalDateTime.of(2022, Month.MAY, 28, 13, 14, 15);
  public static final String NEW_EMAIL_ADDRESS = "straw_hat_luffy@gmail.com";
  public static final String NEW_NAME = "Monkey D. Luffy";

}
