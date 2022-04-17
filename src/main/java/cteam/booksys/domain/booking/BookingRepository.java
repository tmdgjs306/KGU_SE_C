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

    public List<Booking> getBookings(LocalDate date) {
        return em.createQuery("select b from Booking b join fetch b.table", Booking.class)
                .getResultList();
    }

    public void createReservation(Reservation reservation) {
        em.persist(reservation);
    }

    public void createWalkIn(WalkIn walkIn) {
        em.persist(walkIn);
    }

    public void deleteBooking(Booking booking) {
        em.remove(booking);
    }
}
