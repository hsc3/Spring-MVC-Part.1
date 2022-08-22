package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.HandlerAdapter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class V3HandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {

        // 해당 어댑터가 해당 핸들러(컨트롤러)를 처리할 수 있는지 확인
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        // 실제 컨트롤러를 호출하고, 모델 뷰를 반환
        ControllerV3 controller = (ControllerV3) handler; // 캐스팅 : Object -> ControllerV3
        ModelView mv = controller.process(requestParamExtract(request));
        return mv;
    }

    Map<String, String> requestParamExtract(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
