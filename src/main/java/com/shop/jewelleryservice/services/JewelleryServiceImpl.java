package com.shop.jewelleryservice.services;

import com.shop.jewelleryservice.dto.JewelleryItemsDto;
import com.shop.jewelleryservice.entity.JewelleryItems;
import com.shop.jewelleryservice.entity.MetalItems;
import com.shop.jewelleryservice.repositories.JewelleryRepository;
import com.shop.jewelleryservice.repositories.MetalItemsRepository;
import com.shop.jewelleryservice.repositories.TaxesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JewelleryServiceImpl implements JewelleryService{

    private final JewelleryRepository jewelleryRepository;
    private final MetalItemsRepository metalItemsRepository;
    private final TaxesRepository taxesRepository;
    private final ModelMapper modelMapper;


    @Override
    public Map<String, Object> getJewelleryItems() {

        Map<String, Object> result = new HashMap<>();
        result.put("jewelleryItems",jewelleryRepository.findAll());
        result.put("metalItems",metalItemsRepository.findAll());
        result.put("taxes", taxesRepository.findAll());

        return result;
    }

    @Transactional
    @Override
    public JewelleryItems createJewelleryItem(JewelleryItemsDto values) {

        JewelleryItems newJewelItem = modelMapper.map(values, JewelleryItems.class);

        BigDecimal quantity = newJewelItem.getQuantity();
        BigDecimal shippingCharges = newJewelItem.getShippingCharges();
        Integer taxId = newJewelItem.getTaxId();
        Integer availability = newJewelItem.getAvailability();

        Optional<MetalItems> metalItems = metalItemsRepository.findMetalItemsByItem(newJewelItem.getMetalType());
        BigDecimal metalPrice = metalItems.map(MetalItems::getPricePerGram).orElse(new BigDecimal("0.0"));
        BigDecimal baseMakingCharge = metalItems.map(MetalItems::getBaseMakingCharge).orElse(new BigDecimal("0.0"));

        BigDecimal basePrice = quantity.multiply(metalPrice);
        BigDecimal makingCharges = quantity.multiply(baseMakingCharge);
        BigDecimal subTotal = basePrice.add(makingCharges.add(shippingCharges));

        newJewelItem.setMakingCharges(makingCharges);
        newJewelItem.setFinalPrice(calculateFinalPrice(subTotal, taxId));
        newJewelItem.setAvailability(availability);
        newJewelItem.setId(null);
        return jewelleryRepository.save(newJewelItem);
    }

    public BigDecimal calculateFinalPrice(BigDecimal subTotal, Integer taxId){
        BigDecimal finalPrice;
        BigDecimal taxPrice = taxesRepository.getTaxPercentage(taxId);
        finalPrice = subTotal.add(subTotal.multiply(taxPrice.divide(new BigDecimal(100))));
        return finalPrice;
    }

    @Override
    public JewelleryItems updateJewelleryItem(Map<String, Object> values) {
        return null;
    }

    @Override
    public Map<String, Object> deleteJewelleryItem(Map<String, Object> values) {
        return Map.of();
    }


}
