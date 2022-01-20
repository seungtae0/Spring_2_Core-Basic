package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// Ctrl + Shift + T --> Test 자동 생성
@Component
@Qualifier("mainDiscountPolicy") // Qualifier로 이름을 정하고 호출 시 Qualifier로 해당 이름을 넣어주면 해당 빈이 선택 (Primary랑 같이 사용하면 상세한 Qualifier가 우선순위)
@Primary // @Autowired 시 빈이 여러 개인 경우, Primary가 붙으면 우선순위로 선택된다.
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
