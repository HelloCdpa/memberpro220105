package com.icia.member.entity;

import com.icia.member.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id //pk지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment (strategy=오라클/마이에스큐엘 다름)
    @Column(name = "member_id") //컬럼 이름 지정 구분용 숫자 아이디(별도 컬럼 이름 지정할 때)
    private Long id;
    //memberEmail:크기50,unique
    @Column(length = 50, unique = true) //name  값도 따로 지정할 수 있음
    private String memberEmail; //member_email이라는 형태로 저장됨

    //memberPassword:크기20
    @Column(length = 20)
    private String memberPassword;
    //Column 생략하면 default 크기 255로 지정됨
    private String memberName; //기본 varchar는 255

    /*
    DTO 클래스 객체를 전달 받아 Entity 클래스 필드값으로 세팅하고
    Enetity 객체를 리턴하는 메서드 선언
     */
    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO){
        //static 정적 메서드 = 팩토리 메서드 = 클래스 메서드, 객체를 만들지 않고도 바로 호출 가능
        // alt tab introduce~ 우변을 쓰면 좌변 자동 완성
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        return memberEntity;
    }
}
