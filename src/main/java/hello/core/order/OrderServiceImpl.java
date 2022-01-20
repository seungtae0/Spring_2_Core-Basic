package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final이 붙은 대상에 대해 생성자를 만들어 줌 --> 생성자 주입 부분 코드를 그대로 내부적으로 만들어줌
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private DiscountPolicy discountPolicy;

    //final -> 필수값 (값이 없으면 컴파일 에러) *생성자 주입 방식에서만 사용 가능*
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* 의존관계주입 방법1 : 생성자 주입 */ // 불변 !  필수 ! 의존관계에 사용
    ///*  //롬복의 @RequiredArgsConstructor 를 사용하면 아래 코드를 작성한 것과 동일하므로 주석처리 가능
    @Autowired // 생성자 1개인 경우, @Autowired 생략해도 자동 의존관계 주입 됨. (스프링 빈만 해당)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //*/


    /* 의존관계주입 방법2 : 수정자 주입 (setter 방식) 도 가능 */ // 선택 !, 변경 ! 가능성이 있는 의존관계에 사용
    /*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
     */



    /*DIP 위반 : 인터페이스(추상화)가 아닌 구현(구체화)에 의존하였음*/ /*해결하려면, 구현 객체를 누군가 대신 생성해서 주입해줘야 한다*/
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
