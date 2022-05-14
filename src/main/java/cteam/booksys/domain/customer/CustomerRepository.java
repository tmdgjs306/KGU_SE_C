package cteam.booksys.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final EntityManager em;

    public Long createCustomer(Customer customer) {
        em.persist(customer);
        return customer.getId();
    }

    public Customer getCustomerById(Long id) {
        return em.find(Customer.class, id);
    }

    public Customer getCustomer(String name, String phoneNumber) {
        String query = "select c from Customer c where c.name = :name and c.phoneNumber = :pn";
        return em.createQuery(query, Customer.class)
                .setParameter("name", name)
                .setParameter("pn", phoneNumber)
                .getSingleResult();
    }

    public Optional<Customer> getCustomerByLoginId(String loginId) {
        String query = "select c from Customer c where c.loginId = :loginId";
        List<Customer> customers = em.createQuery(query, Customer.class)
                .setParameter("loginId", loginId)
                .getResultList();

        if (customers.isEmpty()) return Optional.empty();
        return Optional.ofNullable(customers.get(0));
    }
}
