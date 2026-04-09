package com.beingatushar.ecommercebackend.service

import com.beingatushar.ecommercebackend.controller.dtos.products.*

interface ProductService {
    ProductDTO createProduct(CreateProductDTO createProductDTO)

    ProductDTO updateProduct(Long id, UpdateProductDTO updateProductDTO)  // id separate, not in DTO
    ProductDTO getProductById(Long id)

    PaginatedResponseDTO<ProductDTO> getAllProducts(ProductFilterDTO filter)

    List<ProductDTO> getAllProductsList()

    void deleteProduct(Long id)

    Map<String, Object> getProductFiltersMetadata()
}