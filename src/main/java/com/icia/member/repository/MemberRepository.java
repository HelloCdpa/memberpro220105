package com.icia.member.repository;

import com.icia.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<해당 Entity 클래스 이름, PK 타입>
//JpaRepository 상속받아 쓸 때는 Repository 어노테이션 필요 없음
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //이메일을 조건으로 회원조회
    /*
    메서드 리턴타입: MemberEntity (Repository는 Entity 만 상대) (select * from member_table where member_email=?)
    메서드 이름: findByMemberEmail(되도록이면 select은 find by)
    메서드 매개변수: String member_email
     */
    MemberEntity findByMemberEmail(String MemberEmail);



}
