package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {

    // URL 매핑 정보
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    // 서블릿 생성과 동시에 URL 매핑 정보를 세팅한다.
    public FrontControllerV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. URL 매핑 정보에서 컨트롤러 조회
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI); // 부모는 자식을 받을 수 있다.

        if (controller == null) { // 컨트롤러가 조회되지 않는 경우 -> 404 에러 응답
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 2. request 메시지의 모든 파라미터를 파라미터 맵에 저장 -> 메서드 별도 생성
        Map<String, String> paramMap = createParameterMap(request);

        // 3. URL의 로직을 실행하고 모델뷰 반환
        ModelView mv = controller.process(paramMap);

        // 4. 뷰의 논리 경로를 상대 경로로 변환해서 뷰어 생성 및 render 실행
        String relativePath = mv.getViewName(); // 논리 경로
        MyView view = viewResolver(relativePath);// 상대 경로의 뷰 반환
        view.render(mv.getModel(), request, response); // 모델(데이터)과 request, response 객체 전달
    }

    private MyView viewResolver(String relativePath) {
        return new MyView("/WEB-INF/views/" + relativePath + ".jsp");
    }

    private Map<String, String> createParameterMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
