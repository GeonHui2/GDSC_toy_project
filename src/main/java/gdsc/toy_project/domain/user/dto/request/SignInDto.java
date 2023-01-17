package gdsc.toy_project.domain.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignInDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String uid;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
