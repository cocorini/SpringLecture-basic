package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 공연 기획자의 역할을 하는 AppConfig가 역할을 할 배우를 구해다줄것임
// App을 구성하는 역할을 하는 class -> 실제 동작에 필요한 "구현 객체를 생성"한다.
// 그럼 코드를 작성하는 입장에서 AppConfig에서만 딸깍딸깍 수정해주면 되는 건가?
// 여기가 구성영역 여기를 제외한 곳은 사용영역임!
// 이렇게 하면 DIP뿐만 아니라, OCP도 지켜지는데 변경에 닫혀있는 것은 여기서 클라이언트코드인 "사용영역"에 해당하는 부분을 변경할 것이 없다는 것을 의미한다.
@Configuration
public class AppConfig {

//    기존의 이러한 방식은 역할에 따른 구현이 잘 보이지 않는다...?
//    역할을 드러나게 하는 것이 중요하다..? -> memberRepository의 역할을 드러나도록..!
//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 내 생각엔 이렇게 코드를 짜게 되면 memberRepository 즉, 멤버 저장소라는 역할을 Memory라는 배우가 맡는다는 것이 잘 보이기 때문인듯?
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 정책의 역할을 고정 할인 정책이 맡았다!
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
