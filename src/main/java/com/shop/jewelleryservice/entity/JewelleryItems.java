package com.shop.jewelleryservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class JewelleryItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String metalType;

    private BigDecimal quantity;

    private Integer taxId;

    private BigDecimal makingCharges;

    private BigDecimal shippingCharges;

    private BigDecimal finalPrice;

    private Integer availability;

    @Override
    public String toString() {
        return "JewelleryItems{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", metalType='" + metalType + '\'' +
                ", quantity=" + quantity +
                ", taxId=" + taxId +
                ", makingCharges=" + makingCharges +
                ", shippingCharges=" + shippingCharges +
                ", finalPrice=" + finalPrice +
                ", availability=" + availability +
                '}';
    }
}
