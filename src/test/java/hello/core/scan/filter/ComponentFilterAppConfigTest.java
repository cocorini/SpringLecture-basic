package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext appConfig = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = appConfig.getBean(BeanA.class);
        assertThat(beanA).isNotNull();

        // MyExcludeComponentScan에 해당하는 클래스인 BeanB는 빈으로 등록되어서는 안된다.
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> appConfig.getBean(BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = {MyIncludeComponent.class}),
            excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = {MyExcludeComponent.class})
    )
    static class ComponentFilterAppConfig {

    }
}
