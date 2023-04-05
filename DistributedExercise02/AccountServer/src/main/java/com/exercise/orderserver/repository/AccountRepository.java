package com.exercise.orderserver.repository;

import com.exercise.orderserver.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>, JpaSpecificationExecutor<AccountEntity> {

}

