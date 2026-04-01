package com.shop.jewelleryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "METAL_ITEMS")
public class MetalItems {

    @Id
    private String item;

    private Double pricePerGram;
}
