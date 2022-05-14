package cteam.booksys.web.manager;

import cteam.booksys.domain.customer.CustomerService;
import cteam.booksys.domain.restaurant.RestaurantService;
import cteam.booksys.domain.table.TableRepository;
import cteam.booksys.web.booking.DateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class MangerController {

    private final RestaurantService rs;
    private final CustomerService cs;
    private final TableRepository tr;

    @GetMapping("/manager/table")
    public String managePage(@ModelAttribute("dateForm") DateForm dateForm, Model model) {

        return "AdminPage";
    }

    @PostMapping("/manager/table")
    public String manageTable(@ModelAttribute DateForm dateForm, @RequestParam Long tableNumber,
                              @SessionAttribute(value = "manager", required = false) Long managerId) {

        LocalTime min = LocalTime.MIN;
        for (int i = 0; i < 24; i += 2) {
            rs.createReservation(1, dateForm.getDate(), min.plusHours(i), tableNumber, managerId);
        }

        return "redirect:/";
    }
}
