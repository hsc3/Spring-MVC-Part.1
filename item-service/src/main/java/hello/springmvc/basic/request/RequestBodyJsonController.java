package hello.springmvc.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HTTP 메시지 바디 조회 방식 (JSON)
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper(); // 객체 형태 변환 도구

    // 1. 서블릿 기존 방식
    @PostMapping("request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);

        // objectMapper.readValue : 메시지 바디 (Json 형식) -> UserData 객체
        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        log.info("username={} age={}", userData.getUsername(), userData.getAge());

        response.getWriter().write("ok");
    }

    // 2. @RequestBody, @ResponseBody 사용
    @ResponseBody
    @PostMapping("request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws JsonProcessingException {
        log.info("messageBody={}", messageBody);

        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        log.info("username={} age={}", userData.getUsername(), userData.getAge());

        return "ok";
    }

    // 3. @RequestBody, @ResponseBody 사용, 메시지 바디 형식(Json -> UserData 객체) 자동 변환
    @ResponseBody
    @PostMapping("request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody UserData userData) {
        log.info("username={} age={}", userData.getUsername(), userData.getAge());

        return "ok";
    }

    // 4. @HttpEntity 사용
    @PostMapping("request-body-json-v4")
    public HttpEntity<String> requestBodyJsonV4(HttpEntity<UserData> httpEntity) {
        UserData userData = httpEntity.getBody();
        log.info("username={} age={}", userData.getUsername(), userData.getAge());

        return new HttpEntity<>("ok");
    }

    // 5. @ResponseBody : UserData 객체 반환
    @ResponseBody
    @PostMapping("request-body-json-v5")
    public UserData requestBodyJsonV5(@RequestBody UserData userData) {
        log.info("username={} age={}", userData.getUsername(), userData.getAge());

        return userData;
    }
}
