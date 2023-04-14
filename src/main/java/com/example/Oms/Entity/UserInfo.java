package com.example.Oms.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String userEmail;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(nullable = false)
    private String userName;

    private String phoneNo;

    private String userAdderss;

    private String areaPin;

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private UserAuthCred userAuthCred;
}

