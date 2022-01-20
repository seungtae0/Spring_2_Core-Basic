package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
        
    }

    static class TestBean {

        //스프링 빈에 등록되지 않았을 경우 메서드 실행X
        @Autowired(required = false) // --> 의존관계가 없으면 아래 메서드 자체가 호출되지 않음
        public void setNoBean1(Member noBean1) { // Member는 스프링 빈이 아님 -> required = false이므로 이 메서드는 호출되지 않음
            System.out.println("noBean1 = " + noBean1);
        }

        //@Nullable
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        //JAVA 8에서 제공하는 Optional
        @Autowired
        public void setNoBean2(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

    }
}
