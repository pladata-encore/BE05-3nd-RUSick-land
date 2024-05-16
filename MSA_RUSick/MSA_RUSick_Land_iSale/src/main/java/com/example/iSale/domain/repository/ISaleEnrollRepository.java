package com.example.iSale.domain.repository;

import com.example.iSale.domain.entity.ISale;
import com.example.iSale.domain.entity.ISaleEnroll;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleEnrollRepository extends JpaRepository<ISaleEnroll, UUID> {
    List<ISaleEnroll> findAllByQualTFIsNull();

    Optional<ISaleEnroll> findByUserId(UUID uuid);
}
