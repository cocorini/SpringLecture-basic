package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회하기")
    void findBeanByName() {
        MemberService memberService = appContext.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 구체 타입보다는 추상 타입으로 조회하는 것이 DIP를 지키는 길!
    @Test
    @DisplayName("이름 없이 타입으로만 조회하기")
    void findBeanByType() {
        MemberService memberService = appContext.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // xxxxx라는 이름을 갖는 bean을 조회했는데 없는 경우 Test 성공
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> appContext.getBean("xxxxx", MemberService.class));
    }
}
