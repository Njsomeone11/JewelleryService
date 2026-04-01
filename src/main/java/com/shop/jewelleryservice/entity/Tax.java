package com.shop.jewelleryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "taxes")
public class Tax {

    @Id
    private int id;

    @Column(name = "name")
    private String taxName;

    private Float percentage;
}
