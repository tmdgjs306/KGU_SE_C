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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RestaurantService {

    private final BookingRepository br;
    private final CustomerRepository cr;
    private final TableRepository tr;

    public void createReservation(int covers, LocalDate date, LocalTime time, Long tno, Long customerId) {
        Tables table = tr.getTable(tno);
        Customer customer = cr.getCustomerById(customerId);
        Reservation reservation = Reservation.createReservation(covers, date, time, table, customer);
        br.saveReservation(reservation);
    }

    public void createWalkIn(int covers, LocalDate date, LocalTime time, Long tno) {
        Tables table = tr.getTable(tno);
        WalkIn walkIn = WalkIn.createWalkIn(covers, date, time, table);
        br.saveWalkIn(walkIn);
    }

    public void removeReservation(Long rId) {
        Reservation reservation = br.getReservation(rId);
        br.deleteBooking(reservation);
    }

    public List<Reservation> getReservationsByDate(LocalDate date) {
        return br.getAllReservations(date);
    }

    public List<Reservation> getReservationsByCustomer(LocalDate date, Long customerId) {
        return br.getAllReservationsByCustomer(date, customerId);
    }

    public List<Tables> findAbleTables(LocalDate date, LocalTime time) {
        List<Reservation> reservations = br.getAllReservations(date);
        List<Reservation> unAbleReservations = reservations.stream()
                .filter(r -> (time.isAfter(r.getTime()) && time.isBefore(r.getEndTime())))
                .collect(Collectors.toList());
        List<Tables> tables = tr.getAllTables();
        tables.removeIf(t -> {
            for (Reservation unAbleReservation : unAbleReservations) {
                if (unAbleReservation.getTables().getNumber().equals(t.getNumber())) return true;
            }
            return false;
        });
        return tables;
    }
}
