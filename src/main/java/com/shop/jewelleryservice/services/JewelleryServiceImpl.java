package com.shop.jewelleryservice.services;

import com.shop.jewelleryservice.repositories.JewelleryRepository;
import com.shop.jewelleryservice.repositories.MetalItemsRepository;
import com.shop.jewelleryservice.repositories.TaxesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JewelleryServiceImpl implements JewelleryService{

    private final JewelleryRepository jewelleryRepository;
    private final MetalItemsRepository metalItemsRepository;
    private final TaxesRepository taxesRepository;


}
