package cteam.booksys.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository cr;

    @Transactional
    public Customer createCustomer(String name, String phoneNumber) {
        Customer customer = new Customer(name, phoneNumber);
        cr.createCustomer(customer);
        return customer;
    }
}
