package com.scaler.productservice.services;

import com.scaler.productservice.dtos.FakeStoreCreateProductDto;
import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductDetails(Long id) throws ProductNotFoundException {
//        FakeStoreProductDto responseDto=
//                restTemplate.getForObject(
//                        "https://fakestoreapi.com/products/" + id,
//                        FakeStoreProductDto.class);
//
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,FakeStoreProductDto.class);

        if(responseEntity.getStatusCode()== HttpStatusCode.valueOf(404)){

        }else if(responseEntity.getStatusCode()==HttpStatusCode.valueOf(500)){

        }
        FakeStoreProductDto responseBody= responseEntity.getBody();
        if(responseBody==null){
            throw new ProductNotFoundException("Product not found");
        }
        return responseEntity.getBody().toProduct();
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, String category) {
        FakeStoreCreateProductDto requestDto=new FakeStoreCreateProductDto();
        requestDto.setTitle(title);
        requestDto.setDescription(description);
        requestDto.setImage(image);
        requestDto.setPrice(price);
        requestDto.setCategory(category);

        FakeStoreCreateProductDto responseDto=restTemplate.postForObject("https://fakestoreapi.com/products", requestDto, FakeStoreCreateProductDto.class);

        return responseDto.toProduct();
    }

    @Override
    public List<Product> getallProducts() {
        FakeStoreProductDto[] responseDto= restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class );

        List<Product> products=new ArrayList<Product>();
        for(FakeStoreProductDto dto: responseDto){
            products.add(dto.toProduct());
        }

        return products;
    }
}
