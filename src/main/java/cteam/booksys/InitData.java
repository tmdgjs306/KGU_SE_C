package cteam.booksys;

import cteam.booksys.domain.customer.Customer;
import cteam.booksys.domain.customer.CustomerRepository;
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
        init.initManager();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class Init {

        private final TableRepository tr;
        private final CustomerRepository cr;

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

        public void initManager() {
            Customer customer = new Customer("managerA", "01012341234", "managerA", "hello");
            customer.setManager(true);
            cr.createCustomer(customer);
        }
    }

}
