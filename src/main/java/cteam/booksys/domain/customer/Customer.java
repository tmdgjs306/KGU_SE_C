package cteam.booksys.domain.customer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String name;

    private String phoneNumber;

    private String loginId;

    private String pw;

    public Customer(String name, String phoneNumber, String loginId, String pw) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.loginId = loginId;
        this.pw = pw;
    }

    protected Customer() {
    }
}
