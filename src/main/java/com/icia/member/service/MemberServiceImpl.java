package com.icia.member.service;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.entity.MemberEntity;
import com.icia.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository mr;

    @Override
    public Long save(MemberSaveDTO memberSaveDTO){
        /*
        1.MemberSaveDTO -> MemberEntity 옮기기
        2.MemberRepository의 save 메서드 호출하면서 MemberEntity 객체 전달
         */

        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);
        return mr.save(memberEntity).getId();//저장된 회원의 아이디번호값을 바로 가지고 올 수 있음(키값)
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {
        /*
        1. MemberRepository로부터 해당 회원의 정보를 MemberEntity로 가져옴
        2. MemberEntity를 MemberDetailDTO로 바꿔서 컨트롤러로 리턴
         */
        //1
        MemberEntity member = mr.findById(memberId).get();

        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(member);
        System.out.println("memberDetailDTO.toString() = " + memberDetailDTO.toString());
        return memberDetailDTO;
    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        //1. 사용자가 입력한 이메일을 조건으로 DB에서 조회 (select * from member_table where member_email=?)
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        //2.비밀번호 일치여부 확인
        if(memberEntity != null) {
            if (memberLoginDTO.getMemberPassword().equals(memberEntity.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

}
