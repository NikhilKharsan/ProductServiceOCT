package com.scaler.productservice.dtos;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;

    public Product toProduct() {
        Product product=new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setPrice(Double.parseDouble(getPrice()));
        Category category=new Category();
        category.setName(getCategory());
        product.setCategory(category);
        product.setDescription(getDescription());
        product.setImageUrl(getImage());

        return product;
    }
}
