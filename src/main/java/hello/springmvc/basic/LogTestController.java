package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. @Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를찾고, 뷰가 렌더링된다
 * 2. @RestController 는 반환값이 String 이면 http 메세지에 다이렉트로 입력된다
 */
@Slf4j
@RestController
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(LogTestController.class); <- lombok으로 대체가능

    @RequestMapping("/log-test")
    public String LogTest() {
        String name = "Spring";

        /**
         * 쓰레드 정보, 클래스 이름 같은 부가정보를 확인가능
         * 시스템 아웃 콘솔 뿐만 아니라 파일이나 네트워크등, 별도의 위치에 남길수 있다
         * 로그의 레벨
         * TRACE>DEBUG>INFO>WARN>ERROR
         * 개발 서버는 DEBUG 출력
         * 운영 서버는 INFO 출력
         */
        log.trace(" trace log = {}", name);
        log.debug(" debug log = {}", name);
        log.info(" ingo Logs = {}", name);
        log.warn(" warm Logs = {}", name);
        log.error(" error Logs = {}", name);

        return "ok";
    }
}
