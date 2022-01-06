package com.icia.member;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.entity.MemberEntity;
import com.icia.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

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
    @Transactional
    @Rollback
    @DisplayName("회원가입 테스트")
    public void memberSaveTest(){
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
        memberSaveDTO.setMemberEmail("테스트 회원 이메일 1");
        memberSaveDTO.setMemberPassword("테스트 비번 1");
        memberSaveDTO.setMemberName("테스트 회원 이름 1");

        ms.save(memberSaveDTO);

    }
    @Test
    @Transactional //테스트 시작할 때 새로운 트랜잭션 시작
    @Rollback //테스트 종료 후 롤백 수행
    @DisplayName("회원조회 테스트")
    public void memberDetailTest(){
        /*
        given : 테스트 조건 설정
            1. 새로운 회원을 등록하고 해당 회원의 번호 (member_id)를 가져옴
        when : 테스트 수행
            2. 위에서 가져온 회원 번호를 가지고 조회 기능 수행
        then : 테스트 결과 검증
            3. 1번에서 가입한 회원의 정보와 2번에서 조회한 회원의 정보가 일치하면 테스트 통과 일치하지 않으면 테스트 실패
         */

        //1.1 테스트용 데이터 객체 생성
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO("조회용회원이메일", "조회용회원비번", "조회용회원이름");
        //1.2 테스트용 데이터를 DB에 저장하고 member_id를 가져옴
        Long memberId =  ms.save(memberSaveDTO);
        //2
        MemberDetailDTO findMember = ms.findById(memberId);
        //3 memberSaveDTO의 이메일 값과 findMember의 이메일 값이 일치하는 지 확인

        assertThat(memberSaveDTO.getMemberEmail()).isEqualTo(findMember.getMemberEmail());

    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("로그인 테스트")
    public void loginTest(){
        //1.테스트용 회원가입 -로그인용 객체 생성 -동인한 이메일 패스워드를 사용하도록 함 - 로그인 수행 - 로그인 결과 true인지 확인
        final String testMemberEmail = "로그인용회원이메일";
        final String testMemberPassword = "로그인용비밀번호";
        final String testMemberName = "로그인용이름";
        //정확한 테스트를 위해 변수 사용

        //given - 조건 : 새로운 회원을 등록(테스트용 회원가입)
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO(testMemberEmail,testMemberPassword,testMemberName);
        ms.save(memberSaveDTO);
        //when - 테스트 수행 : 이메일 / 비밀번호를 가져옴(로그인용 객체 생성)
        MemberLoginDTO loginMember = new MemberLoginDTO(testMemberEmail,testMemberPassword);
        boolean loginResult = ms.login(loginMember);
        //then - 결과 검증 : memberSaveDTO의 이메일 값과 loginMember 이메일 값이 일치하는 지 확인
        assertThat(loginResult).isEqualTo(true);
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("전체조회 테스트")
    public void findAllTest(){
    /*
    member_table에 아무 데이터가 없는 상태에서
    3명의 회원을 가입시킨 후 memberList 사이즈를 조회하여 3이면 테스트 통과
     */
        //given - 조건 : 새로운 회원을 등록(테스트용 회원가입)
       for(int i=0; i<3; i++){
            ms.save(new MemberSaveDTO("리스트회원"+i,"리스트회원pw"+i,"리스트회원이름"+i));
       }
        //intStream 방식 , Arrow Function 화살표 함수
        //rangerClosed 1부터 3까지 수행
//        IntStream.rangeClosed(1,3).forEach(i ->{
//            ms.save(new MemberSaveDTO("리스트회원"+i,"리스트회원pw"+i,"리스트회원이름"+i));
//        });
        List<MemberDetailDTO> all = ms.findAll();

        assertThat(all.size()).isEqualTo(3);



    }





}
