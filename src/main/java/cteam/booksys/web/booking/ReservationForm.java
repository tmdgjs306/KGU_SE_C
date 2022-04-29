package cteam.booksys.web.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data @AllArgsConstructor
public class ReservationForm {

    private String name;

    private String phoneNumber;

    private Integer covers;
}
