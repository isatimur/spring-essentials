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
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void testGetAccountRepository() {
        Account account = repository.findOne(1L);
        assertThat(account.getId(), is(1L));
        assertThat(new BigDecimal(100L), is(closeTo(account.getBalance(), new BigDecimal(0.01))));
    }

    @Test
    public void testGetAmountOfAccounts() {
        long amount = repository.count();
        assertThat(amount, is(3L));
    }

    @Test
    public void testAccounts() {
        List<Account> accounts = repository.findAll();
        assertThat(accounts.size(), is(3));


    }

    @Test
    public void testCreateAccount() {
        long curId = repository.count() + 1L;
        repository.save(new Account(curId, new BigDecimal(250.00)));
        Account account = repository.findOne(curId);
        BigDecimal decimal = account.getBalance();
        assertThat(account.getId(), is(curId));
        assertThat(new BigDecimal(250), is(closeTo(decimal, new BigDecimal(0.01))));

    }

    @Test
    public void testUpdateAccount() {
        Account account = repository.findOne(2L);
        account.setBalance(account.getBalance().add(new BigDecimal(100)));
        repository.save(account);
        Account updatedAccount = repository.findOne(2L);
        assertThat(new BigDecimal(250), is(closeTo(updatedAccount.getBalance(), new BigDecimal(0.01))));

    }

    @Test
    public void testDeleteAccount() {
        repository.delete(1L);
        assertThat(2L, is(repository.count()));
    }

    @Test
    public void testGTE() {
        List<Account> accounts = repository.findAccountByBalanceGreaterThanEqual(new BigDecimal(101));
        assertThat(2, is(accounts.size()));
    }
}