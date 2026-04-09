package com.beingatushar.ecommercebackend.data.entities

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp

import java.time.Instant

@Entity
@Table(name = "banners")
class BannerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    Long id
    @Column(updatable = false)
    String text
    @CreationTimestamp
    @Column(updatable = false)
    Instant createdAt
}
