package cteam.booksys.domain.booking;

import cteam.booksys.domain.table.Tables;
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
    private Tables tables;

    public LocalTime getEndTime() {
        return getTime().plusHours(2);
    }

    public Booking(int covers, LocalDate date, LocalTime time, Tables tables) {
        this.covers = covers;
        this.date = date;
        this.time = time;
        this.tables = tables;
    }

    protected Booking() {

    }
}
