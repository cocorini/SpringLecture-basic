package hello.core.singleton;

import hello.core.AppConfig;

public class SingletonService {

    // 이미 생성한 객체를 재활용하자~ (객체 공유경제)
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자 막아버리기 - 싱글톤으로 생성하려 하므로..!
    private SingletonService() {}
}

/*
* [싱글톤 문제점]
* 구현하는데 코드 자체가 많이 들어감
* DIP 위반 <- 클라이언트가 구체 클래스에 의존
* OCP 위반 가능성 높음 <- 클라이언트가 구체 클래스에 의존하기 때문에...
* 유연하게 테스트하기 어렵다
* private 생성자이므로 자식 클래스를 만들기 어려움
* 안티패턴 <- 유연성이 매우 안좋기 때문
* */

// -> 스프링 프레임워크는 싱글톤의 문제를 모두 해결해준다..!