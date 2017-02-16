package xyz.isatimur.knowledgebase.spring.essentials.accounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.isatimur.knowledgebase.spring.essentials.accounts.entities.Account;
import xyz.isatimur.knowledgebase.spring.essentials.accounts.repositories.AccountRepository;

import java.math.BigDecimal;

/**
 * Created by isati on 15.02.2017.
 */
@Service
@Transactional
public class AccountService {

    final
    AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public BigDecimal getBalance(Long id) {
        return repository.getAccount(id).getBalance();
    }

    public BigDecimal withdraw(Long id, BigDecimal withdrawings) {
        return deposit(id, withdrawings.negate());
    }

    public BigDecimal deposit(Long id, BigDecimal savings) {
        Account account = repository.getAccount(id);
        BigDecimal balance = account.getBalance();
        account.setBalance(balance.add(savings));
        repository.updateAccount(account);
        return balance;
    }

    public void transfer(Long fromId, Long toId, BigDecimal decimal) {
        withdraw(fromId, decimal);
        deposit(toId, decimal);

    }
}
