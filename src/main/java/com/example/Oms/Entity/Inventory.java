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

    private String itemImg = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fstatic.vecteezy.com%2Fsystem%2Fresources%2Fpreviews%2F000%2F556%2F319%2Foriginal%2Fflat-burger-splash-summer-food-vector-illustration.jpg&f=1&nofb=1&ipt=ed9e496ffd78566aa7fe6b2ba59170e0d4abe0059fab497a13037af0a1e78be5&ipo=images";

    private Float itemPrice;

    private int quantityLeft;

    private String itemDescription;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItems> ordersList = new ArrayList<>();

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<ItemReviews> itemReviewsList = new ArrayList<>();

    @ManyToOne
//    @JsonIgnore
    private ShopInfo shopInfo;
}