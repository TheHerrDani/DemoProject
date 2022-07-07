package com.daniel.sipos.demoproject.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserTable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email_address", nullable = false)
  private String emailAddress;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserTable userTable)) {
      return false;
    }
    return Objects.equals(getId(), userTable.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
