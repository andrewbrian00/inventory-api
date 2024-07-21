package com.cajap.app.controllers;

import com.cajap.app.domain.dto.ProductDto;
import com.cajap.app.domain.entities.ProductEntity;
import com.cajap.app.mappers.Mapper;
import com.cajap.app.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private ProductService productService;

    private Mapper<ProductEntity, ProductDto> productMapper;

    public ProductController(ProductService productService, Mapper<ProductEntity, ProductDto> productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping(path = "/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        ProductEntity productEntity = productMapper.mapFrom(product);
        ProductEntity savedProductEntity = productService.createUpdate(productEntity);
        return new ResponseEntity<>(productMapper.mapTo(savedProductEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/products")
    public Page<ProductDto> listProducts(Pageable pageable) {
        Page<ProductEntity> products = productService.findAll(pageable);
        return products.map(productMapper::mapTo);
    }

    @GetMapping(path = "/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {

        Optional<ProductEntity> foundProduct = productService.findById(id);
        return foundProduct.map(
                productEntity -> {
                    ProductDto productDto = productMapper.mapTo(productEntity);
                    return new ResponseEntity<>(productDto, HttpStatus.OK);
                }
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/products/{id}")
    public ResponseEntity<ProductDto> fullUpdateProduct(
            @PathVariable("id") Long id, @RequestBody ProductDto productDto
    ) {
        if (!productService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productDto.setId(id);
        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity savedProductEntity = productService.createUpdate(productEntity);
        return new ResponseEntity<>(
                productMapper.mapTo(savedProductEntity),
                HttpStatus.OK);

    }

    @PatchMapping(path = "/products/{id}")
    public ResponseEntity<ProductDto> partialUpdateProduct(
            @PathVariable("id") Long id, @RequestBody ProductDto productDto
    ) {
        if (!productService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity updatedProduct = productService.partialUpdate(id, productEntity);
        return new ResponseEntity<>(
                productMapper.mapTo(updatedProduct),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {

        productService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
