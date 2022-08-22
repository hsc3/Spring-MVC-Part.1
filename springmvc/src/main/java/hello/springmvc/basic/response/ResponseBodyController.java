package hello.springmvc.basic.response;

import hello.springmvc.basic.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTP 응답 - 메시지 바디 전송 방식 (Text, JSON)
 */
@Slf4j
@Controller
public class ResponseBodyController {

    // --- Text ---
    // 1. 서블릿 기존 방식
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {

        response.getWriter().write("ok");
    }

    // 2. ResponseEntity 사용
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    
    // 3. @ResponseBody 사용
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    // --- JSON ---
    // 1. ResponseEntity 사용
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<UserData> responseBodyJsonV1() {
        UserData userData = new UserData();
        userData.setUsername("choi");
        userData.setAge(20);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    // 2. @ResponseBody, @ResponseStatus 사용
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/response-body-json-v2")
    public UserData responseBodyJsonV2() {
        UserData userData = new UserData();
        userData.setUsername("choi");
        userData.setAge(20);
        return userData;
    }

}
