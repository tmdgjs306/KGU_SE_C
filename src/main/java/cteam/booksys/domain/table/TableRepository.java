package cteam.booksys.domain.table;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TableRepository {

    private final EntityManager em;

    public Table getTable(Long tno) {
        return em.find(Table.class, tno);
    }

    public List<Long> getTableNumbers() {
        List<Table> tables = em.createQuery("select t From Table t", Table.class)
                .getResultList();
        return tables.stream().map(Table::getNumber)
                .collect(Collectors.toList());
    }
}
