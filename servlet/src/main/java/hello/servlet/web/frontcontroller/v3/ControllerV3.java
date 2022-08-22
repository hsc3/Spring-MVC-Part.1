package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // 모델뷰를 반환
    // request, response를 사용하지 않고, 파라미터를 맵에 저장
    ModelView process(Map<String, String> paramMap);
}
