package xyz.isatimur.knowledgebase.spring.essentials.accounts.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.isatimur.knowledgebase.spring.essentials.accounts.config.AppConfig;
import xyz.isatimur.knowledgebase.spring.essentials.accounts.entities.Account;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

/**
 * Created by isati on 16.02.2017.
 * JPA Account Repository Test is validating wherever methods of Account
 * repository working correctly as set of simple calls to CRUD into DATABASE.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class JpaAccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void testGetAccountRepository() {
        Account account = repository.getAccount(1L);
        assertThat(account.getId(), is(1L));
        assertThat(new BigDecimal(100L), is(closeTo(account.getBalance(), new BigDecimal(0.01))));
    }

    @Test
    public void testGetAmountOfAccounts() {
        int amount = repository.getNumberOfAccounts();
        assertThat(amount, is(3));
    }

    @Test
    public void testAccounts() {
        List<Account> accounts = repository.getAccounts();
        assertThat(accounts.size(), is(3));


    }

    @Test
    public void testCreateAccount() {
        Long id = repository.createAccount(new BigDecimal(250.00));
        Account account = repository.getAccount(id);
        BigDecimal decimal = account.getBalance();
        assertThat(account.getId(), is(id));
        assertThat(new BigDecimal(250), is(closeTo(decimal, new BigDecimal(0.01))));

    }

    @Test
    public void testUpdateAccount() {
        Account account = repository.getAccount(2L);
        account.setBalance(account.getBalance().add(new BigDecimal(100)));
        repository.updateAccount(account);
        Account updatedAccount = repository.getAccount(2L);
        assertThat(new BigDecimal(250), is(closeTo(updatedAccount.getBalance(), new BigDecimal(0.01))));

    }

    @Test
    public void testDeleteAccount() {
        repository.deleteAccount(1L);
        assertThat(2, is(repository.getNumberOfAccounts()));
    }

}