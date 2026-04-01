package com.shop.jewelleryservice.repositories;

import com.shop.jewelleryservice.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TaxesRepository extends JpaRepository<Tax, Integer> {

    @Query(value = "select percentage from taxes where id = :id", nativeQuery = true)
    BigDecimal getTaxPercentage(@Param("id") int taxId);
}
