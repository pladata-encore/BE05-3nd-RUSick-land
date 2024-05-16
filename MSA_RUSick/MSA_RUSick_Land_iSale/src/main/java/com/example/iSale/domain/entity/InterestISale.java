package com.example.iSale.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "INTEREST_ISALE")
@Entity @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestISale {
    @Id
    @GeneratedValue
    @Column(name = "INTEREST_ISALE_ID")
    private UUID id;

    @Column(name = "USER_ID", nullable = false)
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "ISALE_ID", nullable = false)
    private ISale isale;
}
