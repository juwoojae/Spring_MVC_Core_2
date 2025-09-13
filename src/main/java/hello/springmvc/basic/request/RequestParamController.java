package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age );

        response.getWriter().write("ok");
    }
    /**
     * @ResponseBody =~ RestController 와 같은 효과를 낸다  response.getWriter().write("ok"); 없어도 됨!!
     * @RequestParam : 요청 파라메터 이름으로 바인딩 . 궅이 서블릿을 안받아와도 된다
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("username={} age={}", memberName, memberAge);
        return "ok";
    }
    /**
     * @RequestParam("xxx") : 에서 변수명이 같다면 변수 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){

        log.info("username={}, age={}", username, age);
        return "ok";  //HTTP 응답 body 에 기입됨
    }

    /**
     * @RequestParam 에서 변수명이 모두 같고, String, int, Interger 등 단순 타입이라면  @RequestParam 자체도 생략이 가능하다
     * 요청 파라메터에 받아온다는 명시적인 표기가 있어야 한다고 생각해서 과하다.
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.info("username={}, age={}", username, age);
        return "ok";  //HTTP 응답 body 에 기입됨
    }

    /**
     * @RequestParam(required = true) 라고 하면 요청파라메터에 필수로 value 가 포함되어야함을 의미
     * required 는 기본값이 true 이다
     * 주의!) key=   이렇게 넘기면  null 이 아닌 "" 빈 문자열로 들어간다
     * <int 는 기본형 vs Integer 의 차이>
     * Integer 는 Wrapper 클래스여서 null  이 올수 있다 vs int 는 기본형이라서 null 이 오지 못한다
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){

        log.info("username={}, age={}", username, age);
        return "ok";  //HTTP 응답 body 에 기입됨
    }

    /**
     * 이미 기본값이 설정되어 있기 때문에 required 가 의미가 없어
     * 주의) 빈문자인 경우에는 "" 이 아닌 defaultValue 로 받아온다
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age){

        log.info("username={}, age={}", username, age);
        return "ok";  //HTTP 응답 body 에 기입됨
    }
    /**
     * 파라메터 요청을 @RequestParam Map, MultiValueMap 의 형태로 받아올수 있음
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"),paramMap.get("age"));
        return "ok";  //HTTP 응답 body 에 기입됨
    }

    /**
     * @ModelAttribute
     * 1. HelloData 객체 생성
     * 2. 요청 파라메터의 이름으로 HelloData 객체의 프로퍼티(getter,setter)를 찾는다, 그리고 해당 프로퍼티의 setter
     * 를 호출해서 파라미터의 값을 입력(바인딩) 한다.
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        //log.info("helloData={}", helloData);
        return "ok";
    }

    /**
     * ModelAttribute 생략 가능
     * String, int, Integer 같은 단순타입 = @RequestParam
     * 나머지 = @ModelAttribute (argument resolver 로 지정해준 타입제외)
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        //log.info("helloData={}", helloData);
        return "ok";
    }
}
