package hello.springmvc.basic.request;

import hello.springmvc.basic.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * HTTP 요청 파라미터 조회 방식
 */
@Slf4j
@Controller
public class RequestParamController {

    // 1. HttpRequestServlet의 getParamter() 사용
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={} age={}", username, age);

        response.getWriter().write("ok"); // 응답
    }

    // 2. @RequestParam 사용
    @ResponseBody // 뷰를 찾지 않고, 문자열을 응답 메시지에 담아서 전송
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge
                                ) {
        log.info("username={} age={}", memberName, memberAge);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age
    ) {
        log.info("username={} age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={} age={}", username, age);

        return "ok";
    }

    // 3. 필수 파라미터 여부 지정
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username, // 필수
                                       @RequestParam(required = false) Integer age     // 선택
                                      ) {
        log.info("username={} age={}", username, age);

        return "ok";
    }

    // 4. 필수 파라미터 여부 - 기본값 적용
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "30") int age
                                      ) {
        log.info("username={} age={}", username, age);

        return "ok";
    }
    
    // 5. 파라미터 Map으로 조회
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamRequired(@RequestParam Map<String, Object> paramMap) {
        log.info("username={} age={}", paramMap.get("username"), paramMap.get("age")); // Map의 key 값으로 조회

        return "ok";
    }
    
    // 요청 파라미터로 객체 자동 생성 - @ModelAttribute 사용
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute UserData userData) {
        log.info("username={} age={}", userData.getUsername(), userData.getAge());
        log.info("userData={}", userData);

        return "ok";
    }

    // 요청 파라미터로 객체 자동 생성 - @ModelAttribute 생략
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(UserData userData) {
        log.info("username={} age={}", userData.getUsername(), userData.getAge());
        log.info("userData={}", userData);

        return "ok";
    }
}
