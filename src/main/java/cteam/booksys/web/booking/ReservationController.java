package cteam.booksys.web.booking;

import cteam.booksys.domain.booking.Reservation;
import cteam.booksys.domain.customer.Customer;
import cteam.booksys.domain.customer.CustomerService;
import cteam.booksys.domain.restaurant.RestaurantService;
import cteam.booksys.domain.table.TableRepository;
import cteam.booksys.domain.table.Tables;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final RestaurantService rs;
    private final CustomerService cs;
    private final TableRepository tr;

    @GetMapping("/reservations/date")
    public String reservationDateForm(@ModelAttribute("dateForm") DateForm dateForm) {

        return "Booking";
    }

    @PostMapping("/reservations/table")
    public String reservationsTableForm(@ModelAttribute("dateForm") DateForm dateForm, Model model) {

        List<Tables> ableTables = rs.findAbleTables(dateForm.getDate(), dateForm.getTime());
        List<Able> ables = getAbleList(ableTables);

        ables.forEach(a -> log.info("{}", a.isAble()));

        model.addAttribute("dateForm", dateForm);
        model.addAttribute("tables", ableTables);
        model.addAttribute("ables", ables);

        return "Table";
    }

    @PostMapping("/reservations/new/form")
    public String reservationInputForm(@ModelAttribute("tableNumber") Long tableNumber,
                                       @ModelAttribute("dateForm") DateForm dateForm,
                                       @ModelAttribute("rForm") ReservationForm rForm) {

        return "p1";
    }

    @PostMapping("/reservations/new")
    public String reservationSubmit(@ModelAttribute("tableNumber") Long tableNumber,
                                    @ModelAttribute("dateForm") DateForm dateForm,
                                    @ModelAttribute("rForm") ReservationForm rForm,
                                    BindingResult bindingResult) {
        //todo Validation
        //todo 로그인 구현하면 수정
        //todo TableCoverCheck Validation
        Tables table = rs.getTable(tableNumber);
        if (table.getPlaces() < rForm.getCovers()) {
            bindingResult.reject("OverCover", "테이블의 최대 인원수 보다 예약인원이 더 많습니다.");
            return "p1";
        }

        Customer customer = cs.createCustomer(rForm.getName(), rForm.getPhoneNumber());
        rs.createReservation(rForm.getCovers(), dateForm.getDate(), dateForm.getTime(), tableNumber, customer.getId());

        return "redirect:/reservations";
    }

    @GetMapping("/reservations")
    public String reservationList(Model model) {
        //todo 로그인 구현하면 수정
        List<Reservation> reservations = rs.getAllReservations();

        model.addAttribute("bookings", reservations);

        return "p2";
    }

    @PostMapping("/reservations/{id}/cancel")
    public String cancel(@PathVariable Long id) {
        rs.removeReservation(id);

        return "redirect:/reservations";
    }

    @GetMapping("/reservations/{id}/update")
    public String update(@PathVariable Long id, Model model) {

        Reservation reservation = rs.getReservation(id);
        List<Tables> ableTables = rs.findAbleTables(reservation.getDate(), reservation.getTime());

        List<Able> ables = getAbleList(ableTables);

        model.addAttribute("reservation", reservation);
        model.addAttribute("tables", ableTables);
        model.addAttribute("ables", ables);

        return "TableChange";
    }

    @PostMapping("/reservations/update")
    public String update(@RequestParam Long id,
                         @RequestParam Long tableNumber) {

        rs.updateTableNumber(id, tableNumber);

        return "redirect:/reservations";
    }

    @Data
    @AllArgsConstructor
    static class Able {

        private boolean able;
    }

    private List<Able> getAbleList(List<Tables> ableTables) {
        List<Able> ables = new ArrayList<>();

        for (Long i = 1L; i <= 8L; i++) {
            boolean flag = false;
            for (Tables table : ableTables) {
                if (table.getNumber().equals(i)) {
                    flag = true;
                    break;
                }
            }
            if (flag) ables.add(new Able(true));
            else ables.add(new Able(false));
        }
        return ables;
    }
}
