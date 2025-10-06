package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;

@Slf4j
@RestController
public class RequestHeaderController {

    //@RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,//언어 정보
                          ///  쿼리 파라메터는 하나의 key 값이 여러개의 value 를 가질수 있다
                          ///   MultiValueMap 은 이것을 허용하는 Map이다
                          @RequestHeader MultiValueMap<String,String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie  //default = true 임
                          ) {
        log.info(headerMap.toString());
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "oK";
    }
    @RestController
    public class HeaderController {

        @GetMapping("/headers")
        public String headers(@RequestHeader Map<String, String> headerMap) {
            headerMap.forEach((key, value) -> {
                System.out.println(key + " = " + value);
            });
            return "ok";
        }
    }
}
