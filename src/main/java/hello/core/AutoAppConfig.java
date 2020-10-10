package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@Component 어노테이션이 붙은 객체를 찾아서 빈으로 등록해준다.
//기존 예제 코드인 AppConfig를 살리기위해서 excludeFilter로 컴포넌트 스캔을 안하도록 설정했다.
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

}
