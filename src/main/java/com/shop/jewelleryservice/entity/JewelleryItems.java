package com.shop.jewelleryservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "JEWELLERY_ITEMS")
public class JewelleryItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String metalType;

    private BigDecimal quantity;

    private BigDecimal makingCharges;

    private BigDecimal finalPrice;

    private int availability;
}
