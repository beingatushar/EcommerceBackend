package com.beingatushar.ecommercebackend.data.repository

import com.beingatushar.ecommercebackend.data.entities.BannerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BannerRepository extends JpaRepository<BannerEntity, Long> {

    @Query("SELECT b.text FROM BannerEntity b ORDER BY b.createdAt DESC LIMIT 1")
    String getLatestBanner()
}