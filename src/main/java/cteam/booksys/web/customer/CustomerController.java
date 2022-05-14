package cteam.booksys.web.customer;

import cteam.booksys.domain.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService cs;

    @GetMapping("/customers/join")
    public String joinForm(@ModelAttribute("joinForm") JoinForm joinForm) {

        return "SignUp";
    }

    @PostMapping("/customers/join")
    private String join(@ModelAttribute JoinForm joinForm, BindingResult bindingResult) {

        cs.createCustomer(joinForm.getName(), joinForm.getPhoneNumber(), joinForm.getLoginId(), joinForm.getPw());

        return "redirect:/login";
    }
}