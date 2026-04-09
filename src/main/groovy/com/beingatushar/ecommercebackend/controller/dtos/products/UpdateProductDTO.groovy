package com.beingatushar.ecommercebackend.controller.dtos.products

class UpdateProductDTO {
    String name
    Integer mrp
    Integer stock
    Boolean isDeleted
    Boolean isFeatured
    String material
    String category
    List<String> tags
    List<String> features
    Integer price
    String description
    String url
}