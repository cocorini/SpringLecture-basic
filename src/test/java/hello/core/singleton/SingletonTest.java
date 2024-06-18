package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 조회: 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        // 조회: 호출할 때마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인하기 - 고객의 요청이 정말 많은 웹어플리케이션의 경우 매 요청마다 새로운 객체를 생성한다는 것은 매우 비효율적이다.
        // 이것을 객체의 싱글톤 패턴을 통한 생성으로 해결한다. -> 클래스의 인스턴스가 단 하나만 생성되는 것을 보장하는 디자인 패턴.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService SingletonService1 = SingletonService.getInstance();
        SingletonService SingletonService2 = SingletonService.getInstance();

        System.out.println("SingletonService1 = " + SingletonService1);
        System.out.println("SingletonService2 = " + SingletonService2);

        assertThat(SingletonService1).isSameAs(SingletonService2);
        // same : ==
        // equal : equals()
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        AnnotationConfigApplicationContext appConig = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = appConig.getBean(MemberService.class);
        MemberService memberService2 = appConig.getBean(MemberService.class);

        // 스프링 빈은 싱글톤패턴으로 이미 작성되어져 있다...!
        // 스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용 가능하도록 만든다.
        // 빈 스코프를 이용해서 매번 새로운 객체를 만들수도 있나봄...?
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
