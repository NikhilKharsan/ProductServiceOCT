package com.scaler.productservice.dtos;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductDto {
    private Long id;
    String title;
    String description;
    String image;
    double price;
    String category;

    public Product toProduct() {
        Product product=new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setPrice(getPrice());
        Category category=new Category();
        category.setName(getCategory());
        product.setCategory(category);
        product.setDescription(getDescription());
        product.setImageUrl(getImage());

        return product;
    }

}
