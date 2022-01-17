package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 탐색할 패키지의 시작 위치 지정 (해당 패키지를 포함해서 하위 패키지를 모두 탐색)
//        basePackages = "hello.core.member",

        // 지정한 클래스의 패키지를 탐색 시작 위치로 지정
//        basePackageClasses = AutoAppConfig.class,

        //basePackage 정보를 지정하지 않으면?! --> @ComponentScan이 붙은 (현재 AutoAppConfig) 설정 정보 클래스의 패키지(hello.core)가 시작 위치가 된다.
        //*권장방법 : 패키지 위치를 지정하지 앟고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것 :)
        
        // 기존에 만들었던 AppConfig도 @Configuration이 붙은 Component로 자동 등록 되기 때문에, 아래 Auto만 적용하기 위해서 제외 처리
        // @ComponentScan 사용 시, --> @Component 애노테이션이 붙은 클래스 -> 스프링 빈으로 자동 등록된다.
        // @Configuration 내부 소스코드에도 @Component 붙어있어서 컴포넌트 스캔의 대상이 된다.
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}

