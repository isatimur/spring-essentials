package xyz.isatimur.knowledgebase.spring.essentials.accounts.repositories;

import org.springframework.stereotype.Repository;
import xyz.isatimur.knowledgebase.spring.essentials.accounts.entities.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by isati on 16.02.2017.
 */
@Repository
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext
    EntityManager em;
    private Long nextid = 4L;

    @Override
    public Account getAccount(Long id) {
        return em
                .find(Account.class, id);
    }

    @Override
    public List<Account> getAccounts() {
        return em
                .createQuery("select a from Account a", Account.class)
                .getResultList();
    }

    @Override
    public int getNumberOfAccounts() {
        String qCount = "select count(a.id) from Account a";
        return ((Long) em.createQuery(qCount).getSingleResult()).intValue();
    }

    @Override
    public Long createAccount(BigDecimal bigDecimal) {
        Long curId = nextid++;
        em.persist(new Account(curId, bigDecimal));
        return curId;
    }

    @Override
    public void updateAccount(Account account) {
        em.merge(account);

    }

    @Override
    public int deleteAccount(Long id) {
        Account account = getAccount(id);
        em.remove(account);
        return 1;
    }
}
