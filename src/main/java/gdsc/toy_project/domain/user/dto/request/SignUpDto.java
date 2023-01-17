package gdsc.toy_project.domain.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String uid;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 4, message = "비밀번호는 4자리 이상 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String username;
}
