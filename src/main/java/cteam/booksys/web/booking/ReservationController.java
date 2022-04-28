package cteam.booksys.web.booking;

import cteam.booksys.domain.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final RestaurantService rs;

    @GetMapping("/reservations/new")
    public String reservation() {
        return null;
    }
}
