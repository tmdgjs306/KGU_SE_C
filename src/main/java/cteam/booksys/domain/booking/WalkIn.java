package cteam.booksys.domain.booking;

import cteam.booksys.domain.table.Tables;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class WalkIn extends Booking{
    public WalkIn(int covers, LocalDate date, LocalTime time, Tables tables) {
        super(covers, date, time, tables);
    }

    protected WalkIn() {
        super();
    }
}
