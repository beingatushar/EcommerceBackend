package com.beingatushar.ecommercebackend.controller.dtos.products

class CreateProductDTO {
    String name
    Integer mrp
    Integer stock
    String material
    String category
    List<String> tags
    List<String> features
    Integer price
    String description
    String url
}