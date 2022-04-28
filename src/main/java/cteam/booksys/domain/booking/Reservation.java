package cteam.booksys.domain.booking;

import cteam.booksys.domain.customer.Customer;
import cteam.booksys.domain.table.Tables;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class Reservation extends Booking{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalTime arrivalTime;

        public Reservation(int covers, LocalDate date , LocalTime time) {
        super(covers, date, time);
    }

    public void addCustomer(Customer customer) {
        setCustomer(customer);
    }

    public void removeCustomer() {
        setCustomer(null);
    }

    public static Reservation createReservation(int covers, LocalDate date, LocalTime time, Tables table, Customer customer) {
        Reservation reservation = new Reservation(covers, date, time);
        reservation.addCustomer(customer);
        reservation.addTable(table);
        return reservation;
    }

    protected Reservation() {
        super();
    }
}
