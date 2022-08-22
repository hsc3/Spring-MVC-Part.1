package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController // 문자열을 응답 메시지 바디에 담아서 전송 (@Controller는 뷰의 논리 이름을 반환 -> 뷰 호출)
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass()); // getClass() == LogTestController.class

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok"; 
    }
}