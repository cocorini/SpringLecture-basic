package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 여기서 MemberService는 인터페이스를 의존(MebmerRepository)하는 동시에, 구현체(MemoryMemberRepository)도 함께 의존한다.
    // DIP를 위반하고 있는 것
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // @Component를 이용해 등록하는 빈에 의존관계를 Autowired를 이용해서 설정해준다.
    @Autowired // appConfig.getBean(MemberRepository.class) 이게 등록된다고 생각하면 됨.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
