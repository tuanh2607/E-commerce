package com.tuanh.ecommerce.entity.user;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invalidated_token")
public class InvalidatedToken {
    @Id
    private String id;

    @Column(nullable = false, name = "expiry_time")
    private Date expiryTime;
}
