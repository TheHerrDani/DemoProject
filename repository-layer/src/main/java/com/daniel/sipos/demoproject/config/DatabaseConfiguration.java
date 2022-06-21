package com.daniel.sipos.demoproject.config;

import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

  @Bean
  public HibernateQueryFactory messageJpaQuery(SessionFactory sessionFactory) {
    return new HibernateQueryFactory(sessionFactory.openSession());
  }

}
