package com.example.Oms.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;

    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate = LocalDateTime.now();

    @Temporal(TemporalType.DATE)
    private LocalDate deliveryDate;

    private String status = "PLACED";

    @ManyToOne
    private Inventory inventory;

    private int cartQuantity;

    @ManyToOne
    @JsonIgnore
    private Orders orders;
}
