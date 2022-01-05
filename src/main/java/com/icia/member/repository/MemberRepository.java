package com.icia.member.repository;

import com.icia.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<해당 Entity 클래스 이름, PK 타입>
//JpaRepository 상속받아 쓸 때는 Repository 어노테이션 필요 없음
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
