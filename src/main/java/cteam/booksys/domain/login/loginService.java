package cteam.booksys.domain.login;

import cteam.booksys.domain.customer.Customer;
import cteam.booksys.domain.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class loginService {

    private final CustomerRepository cr;

    public Customer login(String loginId, String pw) {

        Optional<Customer> optionalCustomer = cr.getCustomerByLoginId(loginId);
        return optionalCustomer.filter(c -> c.getPw().equals(pw))
                .orElse(null);
    }
}
