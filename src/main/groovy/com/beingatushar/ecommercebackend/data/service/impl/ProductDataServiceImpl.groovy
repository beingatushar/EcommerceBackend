package com.beingatushar.ecommercebackend.data.service.impl

import com.beingatushar.ecommercebackend.controller.dtos.products.PaginatedResponseDTO
import com.beingatushar.ecommercebackend.controller.dtos.products.ProductDTO
import com.beingatushar.ecommercebackend.controller.dtos.products.ProductFilterDTO
import com.beingatushar.ecommercebackend.controller.dtos.products.UpdateProductDTO
import com.beingatushar.ecommercebackend.data.entities.ProductEntity
import com.beingatushar.ecommercebackend.data.repository.ProductRepository
import com.beingatushar.ecommercebackend.data.service.ProductDataService
import com.beingatushar.ecommercebackend.exception.definations.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProductDataServiceImpl implements ProductDataService {

    @Autowired
    ProductRepository productRepository

    @Override
    ProductDTO createProduct(ProductDTO product) {
        ProductEntity productEntity = ProductDTO.toProductEntity(product)
        ProductEntity savedEntity = productRepository.save(productEntity)
        return ProductDTO.fromProductEntity(savedEntity)
    }

    @Override
    ProductDTO updateProduct(Long id, UpdateProductDTO updateProductDTO) {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id ${id} not found"))

        // Update only fields that are present in UpdateProductDTO
        if (updateProductDTO.name != null) existingProduct.name = updateProductDTO.name
        if (updateProductDTO.mrp != null) existingProduct.mrp = updateProductDTO.mrp
        if (updateProductDTO.price != null) existingProduct.price = updateProductDTO.price
        if (updateProductDTO.stock != null) existingProduct.stock = updateProductDTO.stock
        if (updateProductDTO.category != null) existingProduct.category = updateProductDTO.category
        if (updateProductDTO.material != null) existingProduct.material = updateProductDTO.material
        if (updateProductDTO.description != null) existingProduct.description = updateProductDTO.description
        if (updateProductDTO.url != null) existingProduct.url = updateProductDTO.url
        if (updateProductDTO.isDeleted != null) existingProduct.isDeleted = updateProductDTO.isDeleted
        if (updateProductDTO.isFeatured != null) existingProduct.isFeatured = updateProductDTO.isFeatured
        if (updateProductDTO.tags != null) existingProduct.tags = updateProductDTO.tags
        if (updateProductDTO.features != null) existingProduct.features = updateProductDTO.features

        ProductEntity updatedEntity = productRepository.save(existingProduct)
        return ProductDTO.fromProductEntity(updatedEntity)
    }

    @Override
    ProductDTO getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id ${id} not found"))
        return ProductDTO.fromProductEntity(productEntity)
    }

    @Override
    PaginatedResponseDTO<ProductDTO> getAllProducts(ProductFilterDTO filter) {
        // Set default values if not provided
        if (filter.page == null) filter.page = 0
        if (filter.size == null) filter.size = 10

        // Create Sort object
        String sortBy = filter.sortBy ?: 'createdAt'
        String sortDir = filter.sortDir ?: 'desc'
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy)
        PageRequest pageRequest = PageRequest.of(filter.page, filter.size, sort)

        // Execute query with filters
        def productPage = productRepository.findProductsWithFilters(
                filter.search,
                filter.category,
                filter.minPrice,
                filter.maxPrice,
                filter.minStock,
                filter.isFeatured,
                pageRequest
        )

        // Convert to DTOs
        List<ProductDTO> productDTOs = productPage.content
                .stream()
                .map { productEntity -> ProductDTO.fromProductEntity(productEntity) }
                .toList()

        // Return paginated response
        return new PaginatedResponseDTO<>(
                productDTOs,
                productPage.number,
                productPage.size,
                productPage.totalElements
        )
    }

    @Override
    List<ProductDTO> getAllProductsList() {
        return productRepository.findAll()
                .stream()
                .map { productEntity -> ProductDTO.fromProductEntity(productEntity) }
                .toList()
    }

    @Override
    void deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id ${id} not found"))
        productEntity.isDeleted = true
        productRepository.save(productEntity)
    }

    @Override
    Map<String, Object> getProductFiltersMetadata() {
        Map<String, Object> metadata = new HashMap<>()
        metadata.put("categories", productRepository.findAllCategories())
        metadata.put("minPrice", productRepository.getMinPrice())
        metadata.put("maxPrice", productRepository.getMaxPrice())
        metadata.put("sortFields", ['name', 'price', 'mrp', 'stock', 'createdAt'])
        return metadata
    }
}