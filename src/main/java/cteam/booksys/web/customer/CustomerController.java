package cteam.booksys.web.customer;

import cteam.booksys.domain.customer.Customer;
import cteam.booksys.domain.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService cs;

    @GetMapping("/customers/join")
    public String joinForm(@ModelAttribute("joinForm") JoinForm joinForm) {

        return "SignUp";
    }

    @PostMapping("/customers/join")
    public String join(@ModelAttribute JoinForm joinForm, BindingResult bindingResult) {

        if (loginIdDuplicatedCheck(joinForm.getLoginId())) {
            bindingResult.rejectValue("loginId", "DuplicatedId", "이미 존재하는 아이디 입니다.");
            return "SignUp";
        }

        cs.createCustomer(joinForm.getName(), joinForm.getPhoneNumber(), joinForm.getLoginId(), joinForm.getPw());

        return "redirect:/login";
    }

    private boolean loginIdDuplicatedCheck(String loginId) {
        List<Customer> customers = cs.getCustomers();
        for (Customer customer : customers) {
            if (customer.getLoginId().equals(loginId)) {
                return true;
            }
        }
        return false;
    }
}