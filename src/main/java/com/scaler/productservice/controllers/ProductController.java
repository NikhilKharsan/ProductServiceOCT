package com.scaler.productservice.controllers;

import com.scaler.productservice.dtos.CreateProductRequestDto;
import com.scaler.productservice.dtos.ErrorDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeStoreProductService;
import com.scaler.productservice.services.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("DatabaseProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getallProducts();
    }
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductDetails(id);

    }
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDto requestDto){
        Product product=productService.createProduct(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getImage(),
                requestDto.getPrice(),
                requestDto.getCategory()

        );
         ResponseEntity<Product> responseEntitiy=new ResponseEntity<>(product, HttpStatusCode.valueOf(201));
         return responseEntitiy;
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorDto> handleNPException() {
//            ErrorDto errorDto=new ErrorDto();
//            errorDto.setMessage("Something went wrong");
//
//            ResponseEntity<ErrorDto> responseEntity=new ResponseEntity<>(errorDto,
//                    HttpStatusCode.valueOf(501));
//            return responseEntity;
//    }
//
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException() {
//        ErrorDto errorDto=new ErrorDto();
//        errorDto.setMessage("Product not found");
//
//        ResponseEntity<ErrorDto> responseEntity=new ResponseEntity<>(errorDto,
//                HttpStatusCode.valueOf(404));
//        return responseEntity;
//    }
}
