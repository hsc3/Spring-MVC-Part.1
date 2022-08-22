package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerV2 extends HttpServlet {

    // URL 매핑 정보
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 서블릿 생성과 동시에 URL 매핑 정보를 세팅한다.
    public FrontControllerV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. URL 매핑 정보에서 컨트롤러 조회
        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI); // 부모는 자식을 받을 수 있다.

        if (controller == null) { // 컨트롤러가 조회되지 않는 경우 -> 404 에러 응답
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 2. 컨트롤러의 로직 실행, 뷰어의 render 메서드 실행
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
