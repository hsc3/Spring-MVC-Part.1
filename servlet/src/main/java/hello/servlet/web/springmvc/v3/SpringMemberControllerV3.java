package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// ModelView 반환 -> String (뷰 이름) 반환
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();
    // GET : 데이터 요청
    // POST : 데이터 저장, 수정
    // @RequestMapping("/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    // HTTP 요청 파라미터를 직접 요청할 수 있다.
    // 스프링에서 제공하는 model 요청
    // @RequestMapping("/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save";
    }

    // @RequestMapping(method=RequestMethod.POST)
    @GetMapping
    public String members(Model model) {

        List<Member> memberList = memberRepository.findAll();

        model.addAttribute("memberList", memberList);
        return "members";
    }
}
