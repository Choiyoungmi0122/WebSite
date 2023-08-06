package com.insight.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPwModifyForm {
    @Size(min = 8, max = 15)
	@NotEmpty(message = "비밀번호 수정은 필수항목입니다.")
    private String password1;

	@Size(min = 8, max = 15)
    @NotEmpty(message = "비밀번호 수정 확인은 필수항목입니다.")
    private String password2;
}
