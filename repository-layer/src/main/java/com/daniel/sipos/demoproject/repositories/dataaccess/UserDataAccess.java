package com.daniel.sipos.demoproject.repositories.dataaccess;

import com.daniel.sipos.demoproject.entities.User;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserDataAccess extends JpaRepository<User, Long> {
}
