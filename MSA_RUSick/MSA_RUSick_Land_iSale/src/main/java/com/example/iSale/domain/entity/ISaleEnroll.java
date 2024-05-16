package com.example.iSale.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ISALE_ENROLL")
public class ISaleEnroll {
    @Id @GeneratedValue
    @Column(name = "ISALE_ENROLL_ID")
    private UUID iSaleEnrollId;

    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "INCOME", nullable = false)
    private long income;

    @Column(name = "CHILD", nullable = false)
    private int child;

    @Column(name = "AGE", nullable = false)
    private int age;

    @Column(name = "QUAL_TF")
    @Setter
    private Boolean qualTF;

    @ManyToOne
    @JoinColumn(name = "ISALE_ID", nullable = false)
    private ISale iSale;
}
