package com.daniel.sipos.demoproject.repositories;

import com.daniel.sipos.demoproject.entities.QUserTable;
import com.daniel.sipos.demoproject.entities.UserTable;
import com.daniel.sipos.demoproject.repositories.dataaccess.UserDataAccess;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserRepository {

  @Autowired
  UserDataAccess userDataAccess;

  @Autowired
  HibernateQueryFactory queryFactory;

  private final QUserTable qUser = QUserTable.userTable;

  public UserTable saveUser(UserTable userTable) {
    return userDataAccess.saveAndFlush(userTable);
  }

  public Optional<UserTable> findUserByEmail(String email) {
    return Optional.ofNullable(queryFactory.selectFrom(qUser)
        .where(qUser.emailAddress.eq(email))
        .fetchFirst());
  }

  public List<UserTable> findUsers() {
    return queryFactory.selectFrom(qUser).fetch();
  }
}
