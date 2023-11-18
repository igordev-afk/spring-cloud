package ru.wwerlosh.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wwerlosh.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
