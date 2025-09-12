package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;


@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 기본 요청
     * URL 속성을 배열[] 로 제공하므로 다중 설정이 가능하다
     * method 설정을 안준다면 , HTTP 메서드 모두 허용 (GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = {"  ", "/hello-go"},  method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }
    /**
     * 편리한 축약 애노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mapping-get-v2");
        return "ok";
    }
    /**
     * PathVariable(경로변수) 사용
     * 변수명이 같으면 생략 가능
     * @pathVariable("userId") String userId ->@PathVariable userId
     * /mapping/userA
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}", data); //템플릿 형식으로 사용할수 있다
        return "ok";
    }
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mappingPath user={}, orderId={}", userId, orderId);
        return "ok";
    }
    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode",
     * params="mode=debug",
     * params="mode!=debug",
     * params={"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }
    /**
     * 특정 헤더 조건 매핑
     * headers="mode",
     * headers="!mode",
     * headers="mode=debug",
     * headers="mode!=debug"
     */
    @GetMapping(value = "/mapping-header", headers="mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }
    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * headers 가 아닌 consumes 를 사용해야 한다
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/j*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
