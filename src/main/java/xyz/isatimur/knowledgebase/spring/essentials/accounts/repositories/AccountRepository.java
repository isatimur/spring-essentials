package xyz.isatimur.knowledgebase.spring.essentials.accounts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.isatimur.knowledgebase.spring.essentials.accounts.entities.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by isati on 15.02.2017.
 * Changed from master branch for using Spring-Data
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAccountByBalanceGreaterThanEqual(BigDecimal amount);
}
