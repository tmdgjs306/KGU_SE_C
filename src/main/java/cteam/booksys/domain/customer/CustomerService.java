package cteam.booksys.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository cr;

    @Transactional
    public void createCustomer(String name, String phoneNumber, String loginId, String pw) {
        Customer customer = new Customer(name, phoneNumber, loginId, pw);
        cr.createCustomer(customer);
    }

    public Customer getCustomer(String name, String phoneNumber) {
        return cr.getCustomer(name, phoneNumber);
    }

    public Customer getCustomerById(Long customerId) {
        return cr.getCustomerById(customerId);
    }

    public List<Customer> getCustomers() {
        return cr.getAllCustomer();
    }
}
