package com.daniel.sipos.demoproject.repositories;

import com.daniel.sipos.demoproject.entities.QUser;
import com.daniel.sipos.demoproject.entities.User;
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

  private final QUser qUser = QUser.user;

  public User saveUser(User user) {
    return userDataAccess.saveAndFlush(user);
  }

  public Optional<User> findUserByEmail(String email) {
    return Optional.ofNullable(queryFactory.selectFrom(qUser)
        .where(qUser.emailAddress.eq(email))
        .fetchFirst());
  }

  public List<User> findUsers() {
    return queryFactory.selectFrom(qUser).fetch();
  }
}
