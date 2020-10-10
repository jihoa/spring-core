package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@Component 어노테이션이 붙은 객체를 찾아서 빈으로 등록해준다.
//기존 예제 코드인 AppConfig를 살리기위해서 excludeFilter로 컴포넌트 스캔을 안하도록 설정했다.
@ComponentScan(
        //basePackages를 통한 탐색할 위치 지정
        //*권장! 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두기!!
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

}
