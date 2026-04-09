package com.beingatushar.ecommercebackend.controller.api

import com.beingatushar.ecommercebackend.controller.dtos.products.*
import com.beingatushar.ecommercebackend.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController {

    @Autowired
    ProductService productService

    @GetMapping
    ResponseEntity<PaginatedResponseDTO<ProductDTO>> getAllProducts(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "minPrice", required = false) Integer minPrice,
            @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(value = "minStock", required = false) Integer minStock,
            @RequestParam(value = "isFeatured", required = false) Boolean isFeatured,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "sortDir", required = false) String sortDir,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        ProductFilterDTO filter = new ProductFilterDTO(
                search: search,
                category: category,
                minPrice: minPrice,
                maxPrice: maxPrice,
                minStock: minStock,
                isFeatured: isFeatured,
                sortBy: sortBy ?: 'createdAt',
                sortDir: sortDir ?: 'desc',
                page: page,
                size: size
        )

        PaginatedResponseDTO<ProductDTO> products = productService.getAllProducts(filter)
        return ResponseEntity.ok(products)
    }

    @GetMapping("/all")
    ResponseEntity<List<ProductDTO>> getAllProductsList() {
        List<ProductDTO> products = productService.getAllProductsList()
        return ResponseEntity.ok(products)
    }

    @GetMapping("/{productId}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") Long id) {
        ProductDTO product = productService.getProductById(id)
        return ResponseEntity.ok(product)
    }

    @GetMapping("/metadata/filters")
    ResponseEntity<Map<String, Object>> getFilterMetadata() {
        Map<String, Object> metadata = productService.getProductFiltersMetadata()
        return ResponseEntity.ok(metadata)
    }

    @PostMapping
    ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTO product) {
        ProductDTO createdProduct = productService.createProduct(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct)
    }

    @PostMapping("/filter")
    ResponseEntity<PaginatedResponseDTO<ProductDTO>> getProductsWithFilter(@RequestBody ProductFilterDTO filter) {
        PaginatedResponseDTO<ProductDTO> products = productService.getAllProducts(filter)
        return ResponseEntity.ok(products)
    }

    @PutMapping("/{productId}")
    ResponseEntity<ProductDTO> updateProduct(
            @PathVariable("productId") Long id,
            @RequestBody UpdateProductDTO product
    ) {
        ProductDTO updatedProduct = productService.updateProduct(id, product)
        return ResponseEntity.ok(updatedProduct)
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long id) {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }
}