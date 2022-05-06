package cteam.booksys.web.login;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data @AllArgsConstructor
public class loginForm {

    @NotEmpty(message = "아이디를 입력해주세요.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 입력해주세요.")
    private String pw;
}
