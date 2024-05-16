package com.example.iSale.domain.repository;

import com.example.iSale.domain.entity.InterestISale;
import com.example.iSale.domain.entity.ISale;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InterestISaleRepository extends JpaRepository<InterestISale, UUID> {
    List<InterestISale> findAllByUserId(UUID userId/*UUID id*/);
    // Optional<InterestISale> findByIsaleAndUserId(ISale iSale, UUID id);
    InterestISale findByIsaleAndUserId(ISale iSale, UUID userId);

    // void findByIsaleAndUserId(ISale iSale, UUID id);
}
