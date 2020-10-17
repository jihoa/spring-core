package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
* @date : 2020-09-26
* @see :
 *  주문 생성 요청이 오면, 회원 정보를 조회하고, 할인 정책을 적용한 다음 주문 객체를 생성해서 반환한다.
 *  메모리 회원 리포지토리와, 고정 금액 할인 정책을 구현체로 생성한다.
 *
 *  문제 상황 : 할인 정책을 바꾼다면 어떻게 될까? 정액할인 -> 정률할인
 *  문제점 :
 *       1. OrderService는 추상(인터페이스) 뿐만 아니라 구현 클래스에도 의존하고 있다. (DIP 위반)
 *       2. 따라서 정책을 변경하는 순간 OrderServiceImpl의 코드도 함께 변경해야 한다. (OCP 위반)
 *  해결 방안 :
 *       1. 인터페이스에만 의존하도록 변경한다. -> But, 구현체가 없는 데 어떻게 코드를 실행할 수 있을까? (NullPointException)
 *       2. 누군가 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다.
 **/
@Component
public class OrderServiceImpl implements OrderService {

    //필드 주입 - 필드 주입은 외부에서 변경이 불가능하여 테스트하기에 힘든 단점이 있다. -> 테스트코드를 제외하고선 안쓰는게 좋다
    private  MemberRepository memberRepository;
    private  DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //일반 메서드 주입 - 한번에 여러 필드를 주입 받을 수 있다. 일반적으로 잘 사용 x
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    /**
     * @see :
     * 단일 책임 원칙을 잘 지킨 부분! OrderService의 입장에서 할인에 대한 부분은 DiscountPolicy에 모두 위임하고 있다.
     * discountPolicy가 없었다면, 할인에 대한 변경사항이 생겼을 때에도 OrderService를 수정해야하는 상황이 발생할 수 있다.
     */
       @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
