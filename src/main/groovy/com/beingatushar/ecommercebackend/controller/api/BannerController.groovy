package com.beingatushar.ecommercebackend.controller.api

import com.beingatushar.ecommercebackend.data.entities.BannerEntity
import com.beingatushar.ecommercebackend.data.repository.BannerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/banner")
class BannerController {
    @Autowired
    BannerRepository bannerRepository

    @GetMapping
    ResponseEntity<String> getBanner() {
        ResponseEntity.ok(bannerRepository.getLatestBanner())
    }

    @PutMapping
    ResponseEntity<String> updateBanner(@RequestParam("text") String text) {
        BannerEntity banner = new BannerEntity()
        banner.text = text
        ResponseEntity.ok(bannerRepository.save(banner).text)
    }

}
