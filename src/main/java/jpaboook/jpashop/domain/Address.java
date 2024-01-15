package jpaboook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter // 변경이 불가하게 해야 하므로 Getter 만
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address () {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
