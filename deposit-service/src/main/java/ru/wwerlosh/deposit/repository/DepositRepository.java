package ru.wwerlosh.deposit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.wwerlosh.deposit.entity.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

}
