package com.shop.jewelleryservice.services;

import com.shop.jewelleryservice.dto.JewelleryItemsDto;
import com.shop.jewelleryservice.entity.JewelleryItems;
import com.shop.jewelleryservice.entity.MetalItems;
import com.shop.jewelleryservice.repositories.JewelleryRepository;
import com.shop.jewelleryservice.repositories.MetalItemsRepository;
import com.shop.jewelleryservice.repositories.TaxesRepository;
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

    @Override
    public JewelleryItems createJewelleryItem(JewelleryItemsDto values) {

        JewelleryItems newJewelItem = modelMapper.map(values, JewelleryItems.class);

        BigDecimal quantity = newJewelItem.getQuantity();
        BigDecimal shippingCharges = newJewelItem.getShippingCharges();
        Integer taxId = newJewelItem.getTaxId();
        Integer availability = newJewelItem.getAvailability();

        String metalItemPrices = getMetalItemPrices(newJewelItem.getMetalType());
        BigDecimal metalPrice = new BigDecimal(metalItemPrices.split("_")[0]);
        BigDecimal baseMakingCharge = new BigDecimal(metalItemPrices.split("_")[1]);

        BigDecimal basePrice = quantity.multiply(metalPrice);
        BigDecimal makingCharges = quantity.multiply(baseMakingCharge);
        BigDecimal subTotal = basePrice.add(makingCharges.add(shippingCharges));

        newJewelItem.setMakingCharges(makingCharges);
        newJewelItem.setFinalPrice(calculateFinalPrice(subTotal, taxId));
        newJewelItem.setAvailability(availability);
        newJewelItem.setId(null);
        return jewelleryRepository.save(newJewelItem);
    }

    public String getMetalItemPrices(String metalType){
        Optional<MetalItems> metalItems = metalItemsRepository.findMetalItemsByItem(metalType);
        BigDecimal metalPrice = metalItems.map(MetalItems::getPricePerGram).orElse(new BigDecimal("0.0"));
        BigDecimal baseMakingCharge = metalItems.map(MetalItems::getBaseMakingCharge).orElse(new BigDecimal("0.0"));
        return metalPrice + "_" + baseMakingCharge;
    }

    public BigDecimal calculateFinalPrice(BigDecimal subTotal, Integer taxId){
        BigDecimal finalPrice;
        if(taxId == 4){
            return subTotal;
        }else {
            BigDecimal taxPrice = taxesRepository.getTaxPercentage(taxId);
            finalPrice = subTotal.add(subTotal.multiply(taxPrice.divide(new BigDecimal(100))));
            return finalPrice;
        }
    }

    @Override
    public JewelleryItems updateJewelleryItem(Long id, JewelleryItemsDto values) {
        JewelleryItems existItem = jewelleryRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        existItem.setName(values.getName());
        existItem.setMetalType(values.getMetalType());
        existItem.setQuantity(values.getQuantity());
        existItem.setShippingCharges(values.getShippingCharges());
        existItem.setTaxId(values.getTaxId());
        existItem.setAvailability(values.getAvailability());

        BigDecimal quantity = existItem.getQuantity();
        BigDecimal shippingCharges = existItem.getShippingCharges();

        String metalItemPrices = getMetalItemPrices(existItem.getMetalType());
        BigDecimal metalPrice = new BigDecimal(metalItemPrices.split("_")[0]);
        BigDecimal baseMakingCharge = new BigDecimal(metalItemPrices.split("_")[1]);

        BigDecimal basePrice = quantity.multiply(metalPrice);
        BigDecimal makingCharges = quantity.multiply(baseMakingCharge);
        BigDecimal subTotal = basePrice.add(makingCharges).add(shippingCharges);

        existItem.setMakingCharges(makingCharges);
        existItem.setFinalPrice(calculateFinalPrice(subTotal, existItem.getTaxId()));

        return jewelleryRepository.save(existItem);
    }

    @Override
    public String deleteJewelleryItem(Long id) {
        if (!jewelleryRepository.existsById(id)) {
            throw new RuntimeException("Item not found with id: " + id);
        }
        else{
            jewelleryRepository.deleteById(id);
            return "Item deleted";
        }
    }
}
