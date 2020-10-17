package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {
    /*
     * <@Component의 빈 등록>
     * 빈 이름 기본 전략 : 맨 앞글자를 소문자로! MemberService -> memberService
     * 빈 이름 직접 지정 : @Component("새로운 이름")
     *
     * <어플리케이션 실행시 로그>
     * 1. org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [~] 후보군이 있어요
     * 2. org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean '~' 빈을 등록했어요
     * 3. org.springframework.beans.factory.support.DefaultListableBeanFactory - Autowiring by type from bean name 'memberServiceImpl' via constructor to bean named 'memoryMemberRepository' 주입했어요.
     */
    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memeberRepository = bean.getMemberRepository();
        System.out.println("memeberRepository = " + memeberRepository);
    }
}
