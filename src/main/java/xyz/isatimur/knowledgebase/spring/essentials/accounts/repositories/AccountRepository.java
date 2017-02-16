package xyz.isatimur.knowledgebase.spring.essentials.accounts.repositories;

import xyz.isatimur.knowledgebase.spring.essentials.accounts.entities.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by isati on 15.02.2017.
 */
public interface AccountRepository {
    Account getAccount(Long id);

    List<Account> getAccounts();

    int getNumberOfAccounts();

    Long createAccount(BigDecimal bigDecimal);

    void updateAccount(Account account);

    int deleteAccount(Long id);
}
