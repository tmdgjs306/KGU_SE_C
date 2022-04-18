package cteam.booksys.domain.booking;

import cteam.booksys.domain.table.Table;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
//for rabase
@Entity
@Getter @Setter
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    private int covers;

    private LocalDate date;

    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_number")
    private Table table;

    public LocalTime getEndTime() {
        return getTime().plusHours(2);
    }
}
