package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @ComponentScan을 이용해서 자동 등록된 빈은 소문자로 시작하는 이름을 갖는다.
@ComponentScan(
        // @Configuration을 이용해서 빈 등록한 애들 충돌 날까봐..?
        // @Configuration 타고 들어가보면 얘도 @Component로 되어있음 그래서 스캔됨 가만히 두면
        // filter를 이용해서 어떤 클래스는 컴포넌트에 추가 하지 않을 것임을 선택할 수 있다 -> excludeFilters
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
        // 설정 정보 클래스의 위치를 즉, Configuration의 위치를 프로젝트 최상단에 둔다. 그러면, 해당 클래스의 패키지가 컴포넌트 스캔의 시작 위치가 된다.
)
// 어노테이션의 기능은 언어적으로 지원하는 것이 아니라 스프링이 지원하는 기능이다.
// 언어적으로 어노테이션 표현은 단지 메타 정보를 추가하는 것 뿐이다.
public class AutoAppConfig {

    // 수동 빈이 자동 빈을 오버라이딩 한다. -> springboot에서 막음 오류 내버림
    @Bean(name="memoryMemberRepository")
    public MemoryMemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }
}
