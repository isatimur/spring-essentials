package xyz.isatimur.knowledgebase.spring.essentials.accounts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.isatimur.knowledgebase.spring.essentials.accounts.entities.Account;

/**
 * Created by isati on 15.02.2017.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
