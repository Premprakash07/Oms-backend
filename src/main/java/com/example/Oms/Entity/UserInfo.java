package com.example.Oms.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInfoId;

    @Column(unique = true, nullable = false)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(nullable = false)
    private String userName;

    private String phoneNo;

    private String address;

    private String areaPin;

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private UserAuthCred userAuthCred;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.DETACH)
    private List<ItemReviews> itemReviewsList;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<Orders> ordersList = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.DETACH)
    private List<ShopReviews> shopReviewsList = new ArrayList<>();
}

