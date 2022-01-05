package com.icia.member.service;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberSaveDTO;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO);
    MemberDetailDTO findById(Long memberId);
}
