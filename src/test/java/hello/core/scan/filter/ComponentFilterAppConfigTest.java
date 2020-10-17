package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanB beanB = ac.getBean("beanB", BeanB.class);
        assertThat(beanB).isNotNull();

        //ac.getBean("beanA", BeanA.class);
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanA",BeanA.class));
    }
    @Configuration
    @ComponentScan(
            //FilterType 옵션 : ANNOTATION, ASSIGNABLE_TYPE, ASPECTJ, REGEX, CUSTOM
            //기본값이라 생략 가능! 스프링 기본 설정에 최대한 맞추어 사용하는 것을 권장.
            includeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
