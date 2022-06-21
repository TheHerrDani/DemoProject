package com.daniel.sipos.demoproject.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class MessageDomain {
  private Long id;

  String fromUserEmail;

  String toUserEmail;

  private String message;

  private LocalDateTime insertionDateTime;
}
