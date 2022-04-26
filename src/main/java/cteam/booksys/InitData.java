package cteam.booksys;

import cteam.booksys.domain.table.Tables;
import cteam.booksys.domain.table.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final Init init;

    @PostConstruct
    public void initTable() {

        init.initTable();
    }

    @Component
    @RequiredArgsConstructor
    static class Init {
        private final TableRepository tr;

        public void initTable() {
            tr.createTable(new Tables(4));
            tr.createTable(new Tables(4));
            tr.createTable(new Tables(6));
            tr.createTable(new Tables(6));
            tr.createTable(new Tables(6));
            tr.createTable(new Tables(2));
            tr.createTable(new Tables(2));
            tr.createTable(new Tables(8));
        }
    }

}
