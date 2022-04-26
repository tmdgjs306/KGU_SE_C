package cteam.booksys.domain.table;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TableRepository {

    private final EntityManager em;

    public Tables getTable(Long tno) {
        return em.find(Tables.class, tno);
    }

    public void createTable(Tables tables) {
        em.persist(tables);
    }

    public List<Long> getTableNumbers() {
        List<Tables> tables = em.createQuery("select t From Tables t", Tables.class)
                .getResultList();
        return tables.stream().map(Tables::getNumber)
                .collect(Collectors.toList());
    }

    public List<Tables> getAllTables() {
        return em.createQuery("select t From Tables t", Tables.class)
                .getResultList();
    }
}
