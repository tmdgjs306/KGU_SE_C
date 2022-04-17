package cteam.booksys.domain.table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Tables {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_number")
    private Long number;

    private int places;

    public Tables(int places) {
        this.places = places;
    }

    protected Tables() {
    }
}
