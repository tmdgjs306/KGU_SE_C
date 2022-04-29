package cteam.booksys.web.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data @AllArgsConstructor
public class DateForm {

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;

    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime time;
}
