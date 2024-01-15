package jpaboook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    /**
     * 연관관계의 주인으로 잡는다.
     */
    @ManyToOne(fetch = FetchType.LAZY) // x to one 인 경우 default 가 EAGER 이다. LAZY 비꾸는거 중요함
    @JoinColumn(name = "member_id") // fk
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // order persist 하면, 자동으로 orderItems를 persist 같이 해줌
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 연관관계의 주인으로 잡는다.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 (ORDER , CAMCEL)
}