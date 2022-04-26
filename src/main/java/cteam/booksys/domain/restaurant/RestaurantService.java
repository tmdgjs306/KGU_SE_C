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

}
