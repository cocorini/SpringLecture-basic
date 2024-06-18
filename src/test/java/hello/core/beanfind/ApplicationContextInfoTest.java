package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = appContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = appContext.getBean(beanDefinitionName);
            System.out.print("Name = " + beanDefinitionName + " ");
            System.out.println("object = " + bean);
        }
    }

    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = appContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = appContext.getBeanDefinition(beanDefinitionName);

            //role ROLE_APPLICATION: 직접 등록한 어플리케이션 빈
            //role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = appContext.getBean(beanDefinitionName);
                System.out.print("Name = " + beanDefinitionName + " || ");
                System.out.println("object = " + bean);
            }
        }
    }
}