package com.example.Oms.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = '')
    private LocalDateTime orderDate;

    @ManyToOne
    private Inventory inventory;

    @ManyToOne
    private UserInfo userInfo;
}
