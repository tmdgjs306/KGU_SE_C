package cteam.booksys.web.booking;

import cteam.booksys.domain.restaurant.RestaurantService;
import cteam.booksys.domain.table.Tables;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final RestaurantService rs;

    @GetMapping("/reservations/date")
    public String reservationDateForm(@ModelAttribute("dateForm") DateForm dateForm) {

        return "Booking";
    }

    @PostMapping("/reservations/table")
    public String reservationsTableForm(@ModelAttribute("dateForm") DateForm dateForm, Model model) {

        List<Tables> ableTables = rs.findAbleTables(dateForm.getDate(), dateForm.getTime());
        model.addAttribute("tables", ableTables);

        return "Table";
    }
}
