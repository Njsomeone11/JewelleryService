package com.shop.jewelleryservice.repositories;

import com.shop.jewelleryservice.entity.MetalItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MetalItemsRepository extends JpaRepository<MetalItems, String> {

    Optional<MetalItems> findMetalItemsByItem(String item);
}
