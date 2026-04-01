package com.shop.jewelleryservice.services;

import com.shop.jewelleryservice.dto.JewelleryItemsDto;
import com.shop.jewelleryservice.entity.JewelleryItems;

import java.util.List;
import java.util.Map;

public interface JewelleryService {
    Map<String, Object> getJewelleryItems();

    JewelleryItems createJewelleryItem(JewelleryItemsDto values);

    JewelleryItems updateJewelleryItem(Map<String, Object> values);

    Map<String, Object> deleteJewelleryItem(Map<String, Object> values);
}
