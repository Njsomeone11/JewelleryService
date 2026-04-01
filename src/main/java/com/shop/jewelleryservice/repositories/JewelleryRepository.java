package com.shop.jewelleryservice.repositories;

import com.shop.jewelleryservice.entity.JewelleryItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JewelleryRepository extends JpaRepository<JewelleryItems, Long> {
}
