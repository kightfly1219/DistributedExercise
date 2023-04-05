package com.example.orderserver.repository;

import com.example.orderserver.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>, JpaSpecificationExecutor<AccountEntity> {

    List<AccountEntity> findByAccountId(String accountId);
}

