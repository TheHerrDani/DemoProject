package com.daniel.sipos.demoproject.repositories;

import com.daniel.sipos.demoproject.entities.Message;
import com.daniel.sipos.demoproject.entities.QMessage;
import com.daniel.sipos.demoproject.entities.QUserTable;
import com.daniel.sipos.demoproject.repositories.dataaccess.MessageDataAccess;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {

  @Autowired
  MessageDataAccess dataAccess;

  @Autowired
  HibernateQueryFactory queryFactory;

  private final QMessage qMessage = QMessage.message1;

  private final QUserTable fromUser = new QUserTable("fromUser");

  private final QUserTable toUser = new QUserTable("toUser");

  public Message saveMessage(Message message) {
    return dataAccess.saveAndFlush(message);
  }

  public List<Message> findAllMessagesByUsers(String fromEmail, String toEmail) {
    return queryFactory
        .selectFrom(qMessage)
        .join(fromUser).on(fromUser.id.eq(qMessage.fromUserTable.id))
        .join(toUser).on(toUser.id.eq(qMessage.toUserTable.id))
        .where(fromUser.emailAddress.eq(fromEmail).and(toUser.emailAddress.eq(toEmail))
            .or(fromUser.emailAddress.eq(toEmail).and(toUser.emailAddress.eq(fromEmail))))
        .orderBy(qMessage.insertionDateTime.asc())
        .fetch();
  }

  public List<Message> findAllMessages() {
    return queryFactory.selectFrom(qMessage)
        .orderBy(qMessage.insertionDateTime.asc())
        .fetch();
  }

  public Optional<Message> findMessageByInsertionTime(LocalDateTime insertionTime) {
    return Optional.ofNullable(queryFactory
        .selectFrom(qMessage)
        .where(qMessage.insertionDateTime.eq(insertionTime))
        .fetchFirst());
  }
}
