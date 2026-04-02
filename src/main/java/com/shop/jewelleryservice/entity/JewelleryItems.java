package com.shop.jewelleryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    @NotNull
    private String metalType;

    @Digits(integer = 6, fraction = 2)
    private BigDecimal quantity;

    @Digits(integer = 2, fraction = 0)
    private Integer taxId;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal makingCharges;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal shippingCharges;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal finalPrice;

    @Digits(integer = 1, fraction = 0)
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
