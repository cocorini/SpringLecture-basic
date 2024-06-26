package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext appConfig = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = appConfig.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}