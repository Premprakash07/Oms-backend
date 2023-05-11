package com.example.Oms.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class ShopReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shopReviewId;

    private int rating;

    private String shopReview;

    @ManyToOne
    @JsonIgnore
    private ShopInfo shopInfo;

    @ManyToOne
    @JsonIgnore
    private UserInfo userInfo;
}
