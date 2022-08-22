package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식 -> 스프링이 스프링 빈으로 자동 등록 (내부에 @Component가 있어서 컨포넌트의 대상이 됨)
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") // 요청 정보를 매핑 (해당 URL이 호출되면 이 메서드가 호출)
    public ModelAndView process() {
        return new ModelAndView("new-form"); // 모델과 뷰 정보를 담아서 반환
    }
}
