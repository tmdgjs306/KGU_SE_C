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
    public WalkIn(int covers, LocalDate date, LocalTime time) {
        super(covers, date, time);
    }

    public static WalkIn createWalkIn(int covers, LocalDate date, LocalTime time, Tables tables) {
        WalkIn walkIn = new WalkIn(covers, date, time);
        walkIn.addTable(tables);
        return walkIn;
    }

    protected WalkIn() {
    }
}
