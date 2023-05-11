package com.example.Oms.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
public class ShopInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shopid;

    @Column(nullable = false)
    private String shopName;

//    @Column(nullable = false)
    private String address;

//    @Column(nullable = false)
    private int areaPin;

//    @Column(nullable = false)
    private int deliverArea;

//    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime openTime;

//    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime closeTime;

//    @Column(nullable = false)
    private String owner;

//    @Column(nullable = false)
    private String gstIn;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNo;

    @OneToMany(mappedBy = "shopInfo", cascade = CascadeType.ALL)
    private List<ShopReviews> shopReviewsList;

    @OneToOne(mappedBy = "shopInfo", cascade = CascadeType.ALL)
    private ShopAuthCred shopAuthCred;
}
