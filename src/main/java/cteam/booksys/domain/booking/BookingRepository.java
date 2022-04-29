package cteam.booksys.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingRepository {

    private final EntityManager em;

    public Reservation getReservation(Long id) {
        String query = "select r from Reservation r join fetch r.tables where r.id = :id";
        return em.createQuery(query, Reservation.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Booking> getAllBookings(LocalDate date) {
        return em.createQuery("select b from Booking b join fetch b.tables", Booking.class)
                .getResultList();
    }

    public List<Reservation> getAllReservations(LocalDate date) {
        String query = "select r from Reservation r join fetch r.tables join fetch r.customer " +
                "where r.date = :date";
        return em.createQuery(query, Reservation.class)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Reservation> getAllReservations2() {
        return em.createQuery("select r from Reservation r join fetch r.tables join fetch r.customer", Reservation.class)
                .getResultList();
    }

    public List<Reservation> getAllReservationsByCustomer(LocalDate date, Long customerId) {
        String query = "select r from Reservation r join fetch r.tables join fetch r.customer " +
                "where r.date = :date and r.customer.id = :customerId";
        return em.createQuery(query, Reservation.class)
                .setParameter("date", date)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public void saveReservation(Reservation reservation) {
        em.persist(reservation);
    }

    public void saveWalkIn(WalkIn walkIn) {
        em.persist(walkIn);
    }

    public void deleteBooking(Booking booking) {
        em.remove(booking);
    }
}
