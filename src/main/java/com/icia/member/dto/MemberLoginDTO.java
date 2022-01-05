package com.icia.member.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginDTO {
    @NotBlank(message = "이메일은 필수입니다.")
    private String memberEmail;
    @NotBlank(message = "비밀번호를 꼭 써주세요.")
    private String memberPassword;

}
