package jpaboook.jpashop.service;

import jpaboook.jpashop.domain.Delivery;
import jpaboook.jpashop.domain.Member;
import jpaboook.jpashop.domain.Order;
import jpaboook.jpashop.domain.OrderItem;
import jpaboook.jpashop.domain.item.Item;
import jpaboook.jpashop.repository.ItemRepository;
import jpaboook.jpashop.repository.MemberRepository;
import jpaboook.jpashop.repository.OrderRepository;
import jpaboook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;
    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 조회
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order); // orderItem 과 delivery는 cascade가 걸려있으므로 persist가 자동으로 됨
        return order.getId();
    }


    // 취소
    @Transactional
    public void cancleOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    // 검색
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }
}
