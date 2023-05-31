package com.example.Oms.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
public class ShopInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shopId;

    @Column(nullable = false)
    private String shopName;

//    @Column(nullable = false)
    private String address;

//    @Column(nullable = false)
    private int areaPin;

//    @Column(nullable = false)
    private String deliverArea;

//    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openTime;

//    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeTime;

//    @Column(nullable = false)
    private String owner;

//    @Column(nullable = false)
    private String gstin;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNo;

    private String category = "other";

    private String photoUrl;

    private int rating = ran.random.nextInt(3, 5);

    @OneToMany(mappedBy = "shopInfo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShopReviews> shopReviewsList;

    @OneToOne(mappedBy = "shopInfo", cascade = CascadeType.ALL)
    private ShopAuthCred shopAuthCred;

    @OneToMany(mappedBy = "shopInfo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Inventory> inventoryList;
}

class ran{
    public static Random random = new Random();
}