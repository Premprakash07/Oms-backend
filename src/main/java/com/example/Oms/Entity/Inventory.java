package com.example.Oms.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    private String itemName;

    private String itemImg;

    private float itemPrice;

    private int quantityLeft;

    private String itemDescription;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Orders> ordersList = new ArrayList<>();

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<ItemReviews> itemReviewsList = new ArrayList<>();

    @ManyToOne
    private ShopInfo shopInfo;
}
