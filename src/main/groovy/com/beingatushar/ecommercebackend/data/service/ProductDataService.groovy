package com.beingatushar.ecommercebackend.data.service

import com.beingatushar.ecommercebackend.controller.dtos.products.PaginatedResponseDTO
import com.beingatushar.ecommercebackend.controller.dtos.products.ProductDTO
import com.beingatushar.ecommercebackend.controller.dtos.products.ProductFilterDTO
import com.beingatushar.ecommercebackend.controller.dtos.products.UpdateProductDTO

interface ProductDataService {
    ProductDTO createProduct(ProductDTO product)

    ProductDTO updateProduct(Long id, UpdateProductDTO product)

    ProductDTO getProductById(Long id)

    PaginatedResponseDTO<ProductDTO> getAllProducts(ProductFilterDTO filter)

    List<ProductDTO> getAllProductsList()

    void deleteProduct(Long id)

    Map<String, Object> getProductFiltersMetadata()
}