package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // HTTP 서블릿 실행 및 request, response 객체 출력
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);   
        System.out.println("response = " + response);

        // HTTP 요청 쿼리 파라미터의 username 출력
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 응답 메시지 생성
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("response message : hello " + username);
    }
}