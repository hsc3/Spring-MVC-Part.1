package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// controller 인터페이스
// 각 controller는 이 인터페이스를 구현한다 -> front controller는 이 인터페이스를 호출해서 구현과 관계없이, 로직의 일관성을 갖는다.
public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
