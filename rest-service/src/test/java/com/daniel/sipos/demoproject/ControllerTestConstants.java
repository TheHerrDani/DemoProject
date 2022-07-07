package com.daniel.sipos.demoproject;

import java.time.LocalDateTime;
import java.time.Month;

public class ControllerTestConstants {
  public static final String All_MESSAGE_JSON =
      "[{\"id\":1,\"fromUserEmail\":\"uzumaki.naruto@gmail.com\",\"toUserEmail\":\"kurusaki" +
          ".ichigo@gmail.com\",\"message\":\"Hello!\"," +
          "\"insertionDateTime\":\"2022-05-28T13:14:15\"}" +
          ",{\"id\":2,\"fromUserEmail\":\"kurusaki.ichigo@gmail.com\"," +
          "\"toUserEmail\":\"uzumaki.naruto@gmail.com\",\"message\":\"Hy\"," +
          "\"insertionDateTime\":\"2022-05-28T13:15:15\"},{\"id\":3,\"fromUserEmail\":" +
          "\"uzumaki.naruto@gmail.com\",\"toUserEmail\":\"kurusaki.ichigo@gmail.com\"," +
          "\"message\":\"Whats up?\",\"insertionDateTime\":\"2022-05-28T13:16:15\"}," +
          "{\"id\":4,\"fromUserEmail\":\"uzumaki.naruto@gmail.com\"," +
          "\"toUserEmail\":\"monkey.d.luffy@gmail.com\",\"message\":\"Buy some cat food pls\"," +
          "\"insertionDateTime\":\"2022-05-28T13:17:15\"},{\"id\":5," +
          "\"fromUserEmail\":\"monkey.d.luffy@gmail.com\"," +
          "\"toUserEmail\":\"uzumaki.naruto@gmail.com\",\"message\":\"Ok\"," +
          "\"insertionDateTime\":\"2022-05-28T13:17:20\"},{\"id\":6," +
          "\"fromUserEmail\":\"uzumaki.naruto@gmail.com\"," +
          "\"toUserEmail\":\"uzumaki.naruto@gmail.com\",\"message\":\"Bye Bye\"," +
          "\"insertionDateTime\":\"2022-05-28T13:17:20\"}]";

  public static final String MESSAGE_BY_USERS_JSON =
      "[{\"id\":1,\"fromUserEmail\":\"uzumaki.naruto@gmail.com\"," +
          "\"toUserEmail\":\"kurusaki.ichigo@gmail.com\",\"message\":\"Hello!\"," +
          "\"insertionDateTime\":\"2022-05-28T13:14:15\"},{\"id\":2," +
          "\"fromUserEmail\":\"kurusaki.ichigo@gmail.com\"," +
          "\"toUserEmail\":\"uzumaki.naruto@gmail.com\",\"message\":\"Hy\"," +
          "\"insertionDateTime\":\"2022-05-28T13:15:15\"},{\"id\":3," +
          "\"fromUserEmail\":\"uzumaki.naruto@gmail.com\"," +
          "\"toUserEmail\":\"kurusaki.ichigo@gmail.com\",\"message\":\"Whats up?\"," +
          "\"insertionDateTime\":\"2022-05-28T13:16:15\"}]";

  public static final String All_USER_JSON =
      "[{\"id\":1,\"emailAddress\":\"uzumaki.naruto@gmail.com\",\"fullName\":\"Uzumaki Naruto\"}," +
          "{\"id\":2,\"emailAddress\":\"kurusaki.ichigo@gmail.com\"," +
          "\"fullName\":\"Kurosaki Ichigo\"}," +
          "{\"id\":3,\"emailAddress\":\"monkey.d.luffy@gmail.com\"," +
          "\"fullName\":\"Monkey D. Luffy\"}]";

  public static final String TEST_USER_EMAIL =
      "{\"id\":1,\"emailAddress\":\"uzumaki.naruto@gmail.com\",\"fullName\":\"Uzumaki Naruto\"}";


  public static final String TEST_EMAIL_ADDRESS_ONE = "uzumaki.naruto@gmail.com";
  public static final String TEST_EMAIL_ADDRESS_TWO = "kurusaki.ichigo@gmail.com";
  public static final LocalDateTime TEST_LOCAL_DATE_TIME =
      LocalDateTime.of(2022, Month.MAY, 28, 13, 14, 15);
  public static final String NEW_EMAIL_ADDRESS = "straw_hat_luffy@gmail.com";
  public static final String NEW_NAME = "Monkey D. Luffy";
}
