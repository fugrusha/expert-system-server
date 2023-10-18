package com.holovko.expertsystem.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "buyer_request")
public class BuyerRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private PropertyEntity property;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private UserInfoEntity buyer;

    private Instant timestamp;

    @Enumerated(EnumType.STRING)
    private BuyerRequestStatus status;
}
