package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        
        // 모든 회원을 조회해서 저장
        List<Member> memberList = memberRepository.findAll();
        
        // 모델에 데이터를 저장하고 모델뷰 반환
        ModelView mv = new ModelView("members");
        mv.getModel().put("memberList", memberList);
        return mv;
    }
}
