package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("DatabaseProductService")
public class DatabaseProductService implements ProductService{


    private ProductRepository productRepository;


    private CategoryRepository categoryRepository;

    public DatabaseProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Product getProductDetails(Long id) throws ProductNotFoundException {
        //TODO: add null check and throw ProductNOtFound exception if product is not found
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        Product productfromDB=optionalProduct.get();
        return productfromDB;
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, String categoryName) {
        Product product=new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category categoryFromDatabase=categoryRepository.findByName(categoryName);
        if(categoryFromDatabase==null){
            Category newcategory=new Category();
            newcategory.setName(categoryName);
            categoryFromDatabase= newcategory;
//            categoryFromDatabase=categoryRepository.save(newcategory);
        }
        product.setCategory(categoryFromDatabase);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getallProducts() {
        return productRepository.findAll();
    }
}
