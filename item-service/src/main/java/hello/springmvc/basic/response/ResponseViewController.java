package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * HTTP 응답 - 뷰 템플릿 호출 방식
 */
@Controller
public class ResponseViewController {

    // 1. ModelAndView 반환
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {

        ModelAndView mav = new ModelAndView("/response/hello");
        mav.addObject("data", "hello!!");

        return mav;
    }

    // 2. Model과 뷰의 논리 이름 반환
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!!");

        return "/response/hello";
    }
    
    // 3. 매핑 URL 경로 = 뷰의 논리 이름
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }

}
