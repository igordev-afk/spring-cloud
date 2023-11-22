package ru.wwerlosh.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wwerlosh.bill.entity.Bill;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> getBillsByAccountId(Long accountId);
}
