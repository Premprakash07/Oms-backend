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
public class ItemReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemReviewId;

    private String itemReview;

    private int rating;

    @ManyToOne
    @JsonIgnore
    private Inventory inventory;

    @ManyToOne
    @JsonIgnore
    private UserInfo userInfo;
}
