package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }
    @PostMapping("/request-body-string-v2")
    public void requestBodyString(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity : HTTP header, body 정보를 편리하게 조회
     * HttpEntity 는 응답에도 사용할수 있다
     * HttpEntity 를 상속 받은 RequestEntity,ResponseEntity (추가기능 제공)
     * 스프링MVC 내부에서 HTTP 메세지 바디를 읽어서 문자나 객체로 변환해서 전달해주는데, 이때 HTTP 메세지 컨버터
     * HttpMessageConverter 라는 기능을 사용한다
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyString(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        //return new ResponseEntity<>("ok",HttpStatus.CREATED); 201 상태 코드 추가가능
        return new HttpEntity<>("ok");
    }
    /**
     * @RequestBody
     * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     * @ResponseBody
     * - 메시지 바디 정보 직접 반환(view 조회X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringv4(@RequestBody String messageBody){
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
