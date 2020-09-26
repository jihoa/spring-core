package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
}
