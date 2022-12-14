package SpringExample.inflearn.order;

import SpringExample.inflearn.discount.DiscountPolicy;
import SpringExample.inflearn.discount.FixDiscountPolicy;
import SpringExample.inflearn.discount.RateDiscountPolicy;
import SpringExample.inflearn.member.Member;
import SpringExample.inflearn.member.MemberRepository;
import SpringExample.inflearn.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
