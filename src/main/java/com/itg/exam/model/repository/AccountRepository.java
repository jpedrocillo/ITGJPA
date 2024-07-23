package com.itg.exam.model.repository;

import com.itg.exam.model.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDto,Long> {

}
