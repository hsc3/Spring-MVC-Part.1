package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /v1/~~ 의 어떤 URL이든 접속하면 해당 "프론트 컨트롤러"가 호출된다.
@WebServlet(name = "frontControllerV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1 extends HttpServlet {

    // URL 매핑 정보
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 서블릿 생성과 동시에 URL 매핑 정보를 세팅한다.
    public FrontControllerV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. URL 매핑 정보에서 컨트롤러 조회
        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(requestURI); // 부모는 자식을 받을 수 있다.

        if (controller == null) { // 컨트롤러가 조회되지 않는 경우 -> 404 에러 응답
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 2. 컨트롤러의 로직 실행 (다형성 이용)
        controller.process(request, response);
    }
}
