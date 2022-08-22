package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        
        // 모든 회원 조회 -> 모델에 회원 데이터 저장 -> viewName 반환
        List<Member> memberList = memberRepository.findAll();
        model.put("memberList", memberList);
        return "members";
    }
}
