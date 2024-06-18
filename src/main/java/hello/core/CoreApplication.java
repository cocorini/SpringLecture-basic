package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 스프링 부트 사용하면 자동 컴포넌트 스캔 됨ㅋㅋ 해당 어노테이션 안에 ComponentScan이 존재!
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}