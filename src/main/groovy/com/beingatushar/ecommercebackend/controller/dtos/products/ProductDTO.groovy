package com.beingatushar.ecommercebackend.controller.dtos.products

import com.beingatushar.ecommercebackend.data.entities.ProductEntity

import java.time.Instant

class ProductDTO {
    Long id
    String name
    Integer mrp
    Integer stock
    Boolean isDeleted = false
    Boolean isFeatured = false
    String material
    String category
    List<String> tags = []
    List<String> features = []
    Integer price
    String description
    String url
    Instant createdAt
    Instant updatedAt

    static ProductDTO fromCreateProductDTO(CreateProductDTO createProductDTO) {
        if (!createProductDTO) return null

        new ProductDTO(
                name: createProductDTO.name,
                mrp: createProductDTO.mrp,
                stock: createProductDTO.stock,
//                isDeleted: createProductDTO.isDeleted ?: false,
//                isFeatured: createProductDTO.isFeatured ?: false,
                material: createProductDTO.material,
                category: createProductDTO.category,
                tags: createProductDTO.tags ?: [],
                features: createProductDTO.features ?: [],
                price: createProductDTO.price,
                description: createProductDTO.description,
                url: createProductDTO.url
        )
    }

    static ProductDTO fromUpdateProductDTO(UpdateProductDTO updateProductDTO) {
        if (!updateProductDTO) return null

        new ProductDTO(
                id: updateProductDTO.id,
                name: updateProductDTO.name,
                mrp: updateProductDTO.mrp,
                stock: updateProductDTO.stock,
//                isDeleted: updateProductDTO.isDeleted,
                isFeatured: updateProductDTO.isFeatured,
                material: updateProductDTO.material,
                category: updateProductDTO.category,
                tags: updateProductDTO.tags,
                features: updateProductDTO.features,
                price: updateProductDTO.price,
                description: updateProductDTO.description,
                url: updateProductDTO.url
        )
    }

    static ProductDTO fromProductEntity(ProductEntity entity) {
        if (!entity) return null

        new ProductDTO(
                id: entity.id,
                name: entity.name,
                mrp: entity.mrp,
                stock: entity.stock,
                isDeleted: entity.isDeleted,
                isFeatured: entity.isFeatured,
                material: entity.material,
                category: entity.category,
                tags: entity.tags,
                features: entity.features,
                price: entity.price,
                description: entity.description,
                url: entity.url,
                createdAt: entity.createdAt,
                updatedAt: entity.updatedAt
        )
    }

    static ProductEntity toProductEntity(ProductDTO dto) {
        if (!dto) return null

        new ProductEntity(
                id: dto.id,
                name: dto.name,
                mrp: dto.mrp,
                stock: dto.stock,
                isDeleted: dto.isDeleted ?: false,
                isFeatured: dto.isFeatured ?: false,
                material: dto.material,
                category: dto.category,
                tags: dto.tags ?: [],
                features: dto.features ?: [],
                price: dto.price,
                description: dto.description,
                url: dto.url
        )
    }
}