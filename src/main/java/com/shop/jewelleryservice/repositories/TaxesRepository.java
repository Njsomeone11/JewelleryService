package com.shop.jewelleryservice.repositories;

import com.shop.jewelleryservice.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxesRepository extends JpaRepository<Tax, Integer> {
}
