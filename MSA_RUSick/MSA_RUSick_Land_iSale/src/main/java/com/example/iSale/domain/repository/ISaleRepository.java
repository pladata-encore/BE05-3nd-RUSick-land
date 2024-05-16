package com.example.iSale.domain.repository;

import com.example.iSale.domain.entity.ISale;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<ISale, UUID> {
}
