package com.shop.jewelleryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "METAL_ITEMS")
public class MetalItems {

    @Id
    private String item;

    private BigDecimal pricePerGram;

    private BigDecimal baseMakingCharge;

    @Override
    public String toString() {
        return "MetalItems{" +
                "item='" + item + '\'' +
                ", pricePerGram=" + pricePerGram +
                ", baseMakingCharge=" + baseMakingCharge +
                '}';
    }
}
