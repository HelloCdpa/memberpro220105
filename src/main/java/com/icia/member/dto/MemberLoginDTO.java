package com.icia.member.dto;

import com.icia.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginDTO {
    @NotBlank(message = "이메일은 필수입니다.")
    private String memberEmail;
    @NotBlank(message = "비밀번호를 꼭 써주세요.")
    private String memberPassword;

   public static MemberLoginDTO memberLoginDTO(MemberEntity member){
       MemberLoginDTO memberLoginDTO = new MemberLoginDTO();
       memberLoginDTO.setMemberEmail(member.getMemberEmail());
       memberLoginDTO.setMemberPassword(member.getMemberPassword());
       return memberLoginDTO;
   }







}
