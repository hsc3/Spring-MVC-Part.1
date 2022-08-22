package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// 컨트롤러를 대신해서 JSP (view)를 호출하는 역할 -> 각 컨트롤러의 중복되는 로직 (코드) 제거
public class MyView {

    private String viewPath; // URL 경로

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // URL 경로를 받아서 URL에 매핑된 jsp (view)를 실행한다.
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        modelDataExtract(model, request); // 모델에 담긴 (key, value)의 데이터를 request 객체에 저장
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private void modelDataExtract(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
