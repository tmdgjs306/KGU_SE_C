package cteam.booksys.web;

import cteam.booksys.LoginConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@SessionAttribute(value = LoginConst.LOGIN_CUSTOMER, required = false) Long customerId) {
        if (customerId == null) {
            return "redirect:/login";
        }
        return "Main";
    }
}
