package cteam.booksys.web.booking;

import cteam.booksys.domain.restaurant.RestaurantService;
import cteam.booksys.domain.table.Tables;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final RestaurantService rs;

    @GetMapping("/reservations/date")
    public String reservationDateForm(@ModelAttribute("dateForm") DateForm dateForm) {

        return "Booking";
    }

    @PostMapping("/reservations/table")
    public String reservationsTableForm(@ModelAttribute("dateForm") DateForm dateForm, Model model) {

        List<Tables> ableTables = rs.findAbleTables(dateForm.getDate(), dateForm.getTime());
        List<Able> ables = new ArrayList<>();

        boolean flag = false;
        for (Long i = 1L; i <= 8L; i++) {
            for (Tables table : ableTables) {
                if (table.getNumber().equals(i)) {
                    flag = true;
                    break;
                }
            }
            if (flag) ables.add(new Able(true));
            else ables.add(new Able(false));
        }

        ables.forEach(a -> log.info("{}", a.isAble()));

        model.addAttribute("tables", ableTables);
        model.addAttribute("ables", ables);

        return "Table";
    }

    @Data @AllArgsConstructor
    static class Able {
        private boolean isAble;
    }

}
