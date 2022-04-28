package cteam.booksys.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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

    public Customer getCustomer(String name, String pn) {
        String query = "select c from Customer c where c.name = :name and c.phoneNumber = :pn";
        return em.createQuery(query, Customer.class)
                .setParameter("name", name)
                .setParameter("pn", pn)
                .getSingleResult();
    }
}
