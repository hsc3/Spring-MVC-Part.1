package hello.springmvc.basic;

import lombok.Data;

@Data // (lombok) Getter, Setter, ToString, RequiredArgsConstructior 등을 자동 생성
public class UserData {

    private String username;
    private int age;
}