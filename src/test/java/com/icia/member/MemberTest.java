package com.icia.member;

import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {
    /*
     MemberService.save() 메서드가 잘 동작하는 지 테스트

     회원가입 테스트
     save.html 에서 회원정보 입력 후 가입 클릭
     db 확인
        안녕
    */
    @Autowired
    private MemberService ms;
    
    @Test
    @DisplayName("회원가입 테스트")
    public void memberSaveTest(){
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
        memberSaveDTO.setMemberEmail("테스트 회원 이메일 1");
        memberSaveDTO.setMemberPassword("테스트 비번 1");
        memberSaveDTO.setMemberName("테스트 회원 이름 1");

        ms.save(memberSaveDTO);

    }

}
