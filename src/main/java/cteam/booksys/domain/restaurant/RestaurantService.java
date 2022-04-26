package cteam.booksys.domain.restaurant;

import cteam.booksys.domain.booking.Booking;
import cteam.booksys.domain.booking.BookingRepository;
import cteam.booksys.domain.booking.Reservation;
import cteam.booksys.domain.booking.WalkIn;
import cteam.booksys.domain.customer.Customer;
import cteam.booksys.domain.customer.CustomerRepository;
import cteam.booksys.domain.table.TableRepository;
import cteam.booksys.domain.table.Tables;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final BookingRepository br;
    private final CustomerRepository cr;
    private final TableRepository tr;

    public List<Booking> getBookings(LocalDate date) {
        return br.getBookings(date);
    }

    public Customer getCustomer(String name, String phone) {
        return cr.getCustomer(name, phone);
    }

    public Tables getTable(Long tno) {
        return tr.getTable(tno);
    }

    public List<Long> getTableNumbers() {
        return tr.getTableNumbers();
    }

    @Transactional
    public Booking makeReservation(int covers, LocalDate date, LocalTime time,
                                   Long tno, String name, String phone) {
        Tables table = tr.getTable(tno);
        Customer customer = cr.getCustomer(name, phone);
        Reservation reservation = Reservation.createReservation(covers, date, time, table, customer, null);
        br.saveReservation(reservation);
        return reservation;
    }

    @Transactional
    public Booking makeWalkIn(int covers, LocalDate date,
                              LocalTime time, Long tno) {
        Tables table = tr.getTable(tno);
        WalkIn walkIn = new WalkIn(covers, date, time, table);
        br.saveWalkIn(walkIn);
        return walkIn;
    }

    @Transactional
    public void removeBooking(Booking booking) {
        br.deleteBooking(booking);
    }
}
