package com.beingatushar.ecommercebackend.service.impl

import com.beingatushar.ecommercebackend.controller.dtos.products.*
import com.beingatushar.ecommercebackend.data.service.ProductDataService
import com.beingatushar.ecommercebackend.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDataService productDataService;

    @Override
    ProductDTO createProduct(CreateProductDTO product) {
        ProductDTO productDTO = ProductDTO.fromCreateProductDTO(product)
        return productDataService.createProduct(productDTO)
    }

    @Override
    ProductDTO updateProduct(Long id, UpdateProductDTO product) {
        return productDataService.updateProduct(id, product)
    }

    @Override
    ProductDTO getProductById(Long id) {
        return productDataService.getProductById(id)
    }

    @Override
    PaginatedResponseDTO<ProductDTO> getAllProducts(ProductFilterDTO filter) {
        return productDataService.getAllProducts(filter)
    }

    @Override
    List<ProductDTO> getAllProductsList() {
        return productDataService.getAllProductsList()
    }

    @Override
    void deleteProduct(Long id) {
        productDataService.deleteProduct(id)
    }

    @Override
    Map<String, Object> getProductFiltersMetadata() {
        return productDataService.getProductFiltersMetadata()
    }
}