package xyz.isatimur.knowledgebase.spring.essentials.accounts.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

/**
 * Created by isati on 16.02.2017.
 * Validating that business logic perspective is tested well.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testDeposit() {
        BigDecimal start = accountService.getBalance(1L);
        BigDecimal newIncome = new BigDecimal("190.00");
        BigDecimal finish = start.add(newIncome);
        accountService.deposit(1L, newIncome);
        assertThat(finish, is(closeTo(accountService.getBalance(1L), new BigDecimal("0.01"))));

    }


    @Test
    public void testWithdraw() {
        BigDecimal start = accountService.getBalance(1L);
        BigDecimal refund = new BigDecimal("10.00");
        BigDecimal finish = start.subtract(refund);
        accountService.withdraw(1L, refund);
        assertThat(finish, is(closeTo(accountService.getBalance(1L), new BigDecimal("0.01"))));

    }

    @Test
    public void testTransfer() {
        BigDecimal start1 = accountService.getBalance(1L);
        BigDecimal start2 = accountService.getBalance(2L);

        BigDecimal transferMoney = new BigDecimal("90.00");
        accountService.transfer(1L, 2L, transferMoney);

        BigDecimal finish1 = accountService.getBalance(1L);
        BigDecimal finish2 = accountService.getBalance(2L);

        assertThat(start1.subtract(transferMoney), is(closeTo(finish1, new BigDecimal("0.01"))));
        assertThat(start2.add(transferMoney), is(closeTo(finish2, new BigDecimal("0.01"))));

    }

}