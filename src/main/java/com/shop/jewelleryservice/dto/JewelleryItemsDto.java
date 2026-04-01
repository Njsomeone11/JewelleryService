package com.shop.jewelleryservice.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class JewelleryItemsDto {

    private String name;
    private String metalType;
    private BigDecimal quantity;
    private Integer taxId;
    private BigDecimal shippingCharges;
    private Integer availability;


}
