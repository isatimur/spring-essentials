package xyz.isatimur.knowledgebase.spring.essentials.accounts.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by isati on 15.02.2017.
 */
//@Entity

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Account {
    @Id
    private Long id;
    private BigDecimal balance;
}
