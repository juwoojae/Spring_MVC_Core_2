package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * RestController 를 사용하면 모든 Controller 에 @ResponseBody 가 적용
 * RestController 는 말 그대로 RestAPI를 만들 때 사용하는 컨트롤러이다
 * */
@Slf4j
@Controller
public class responseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * HttpEntity 를 상속받은  ResponseEntity 를 사용한다
     * HttpEntity 는 HTTP 메세지의 헤더, 바디 정보를 가지고 있다. ResponseEntity 는 여기 더해서 응답 코드를 추가할수 있음
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(HttpServletResponse response) throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(HttpServletResponse response) throws IOException {
        return "ok";
    }

    /**
     * 메세지 컨버터로 인해서 JOSN 형식으로 반환된다
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * @ResponseBody를 사용하면 응답 코드를 포함을 못시킨다
     * @ResponseStatus 를 사용하면 응답 코드를 포함 시킬수 있다
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}