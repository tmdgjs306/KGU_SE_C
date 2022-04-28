package cteam.booksys.domain.restaurant;

import cteam.booksys.domain.booking.BookingRepository;
import cteam.booksys.domain.booking.Reservation;
import cteam.booksys.domain.customer.Customer;
import cteam.booksys.domain.customer.CustomerRepository;
import cteam.booksys.domain.table.Tables;
import org.apache.tomcat.jni.Local;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RestaurantServiceTest {

    @Autowired private RestaurantService rs;
    @Autowired private CustomerRepository cr;
    @Autowired private BookingRepository br;

    @Test
    void createReservationAndGetReservationByDateTest() {
        //given
        Customer customer = new Customer("memberA", "123");
        Long customerId = cr.createCustomer(customer);
        //when
        rs.createReservation(3, LocalDate.of(2022, 4, 25), LocalTime.of(11, 11), 3L, customerId);
        List<Reservation> reservationsByDate = rs.getReservationsByDate(LocalDate.of(2022, 4, 25));
        //then
        assertThat("memberA").isEqualTo(reservationsByDate.get(0).getCustomer().getName());
    }

    @Test
    void findAbleTablesTest() {
        //given
        Customer customer = new Customer("memberA", "123");
        Long customerId = cr.createCustomer(customer);
        rs.createReservation(3, LocalDate.of(2022, 4, 25), LocalTime.of(12, 0), 3L, customerId);
        rs.createReservation(3, LocalDate.of(2022, 4, 25), LocalTime.of(9, 0), 3L, customerId);
        rs.createReservation(3, LocalDate.of(2022, 4, 25), LocalTime.of(9, 0), 4L, customerId);
        rs.createReservation(3, LocalDate.of(2022, 4, 25), LocalTime.of(9, 0), 6L, customerId);
        rs.createReservation(3, LocalDate.of(2022, 4, 25), LocalTime.of(17, 0), 3L, customerId);
        //when
        List<Tables> tables = rs.findAbleTables(LocalDate.of(2022, 4, 25), LocalTime.of(10, 0));
        //then
        tables.forEach(t -> System.out.println(t.getNumber()));
        assertThat(tables.size()).isEqualTo(5);
    }
}