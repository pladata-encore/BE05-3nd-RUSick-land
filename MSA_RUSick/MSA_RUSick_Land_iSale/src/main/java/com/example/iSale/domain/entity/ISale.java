package com.example.iSale.domain.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ISALE")
@Getter @AllArgsConstructor
@NoArgsConstructor
@Builder
public class ISale {
    @Id @GeneratedValue
    @Column(name = "ISALE_ID") // 분양 id
    private UUID iSaleId;

    @Column(name = "ISALE_IN") // 입주 가능일
    private String iSaleIn;

    @Column(name = "ISALE_SELL_PRICE") // 분양가
    private Long iSaleSellPrice;

    @Column(name = "ISALE_CATEGORY")
    private Integer iSaleCategory;

    @Column(name = "ISALE_NAME")
    private String iSaleName;

    @Column(name = "ISALE_AREA")
    private String iSaleArea;

    @Column(name = "ISALE_ADDRESS")
    private String iSaleAddress;

    @OneToMany(mappedBy = "isale", cascade = CascadeType.REMOVE)
    private List<InterestISale> interestISales;

    @OneToMany(mappedBy = "iSale", cascade = CascadeType.REMOVE)
    private List<ISaleEnroll> iSaleEnrolls;
}
