package cteam.booksys.web.login;

import cteam.booksys.LoginConst;
import cteam.booksys.domain.customer.Customer;
import cteam.booksys.domain.login.LoginSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginSerivce loginSerivce;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute loginForm loginForm) {

        return "LoginPage";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") loginForm loginForm, BindingResult bindingResult,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "LoginPage";
        }

        Customer loginCustomer = loginSerivce.login(loginForm.getLoginId(), loginForm.getPw());

        if (loginCustomer == null) {
            bindingResult.reject("loginFail" ,"아이디 혹은 패스워드가 맞지 않습니다.");
            return "LoginPage";
        }

        HttpSession session = request.getSession(true);
        if (loginCustomer.isManager()) {
            session.setAttribute("manager", loginCustomer.getId());
            session.setAttribute(LoginConst.LOGIN_CUSTOMER, loginCustomer.getId());
        }
        else session.setAttribute(LoginConst.LOGIN_CUSTOMER, loginCustomer.getId());

        return "redirect:/";
    }
}
