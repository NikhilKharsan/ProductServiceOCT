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

@Service("databaseProductService")
public class DatabaseProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public DatabaseProductService(ProductRepository productRepository,
                                  CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductDetails(Long id) throws ProductNotFoundException {
        // TODO: Add null check and throw ProductNotFound exception if product is not found
        Optional<Product> productOptionalFromDb = productRepository.findById(id);
        if(productOptionalFromDb.isEmpty()) {
            // Throw an exception product not found
            System.out.println("Product not found");
            return null;
        }

        Product productFromDb = productOptionalFromDb.get();
        System.out.println(productFromDb.getTitle());
        return productFromDb;
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, String categoryName) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category categoryFromDatabase = categoryRepository.findByName(categoryName);
        if(categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setName(categoryName);

            categoryFromDatabase = newCategory;

            // categoryFromDatabase = categoryRepository.save(category);
        }

        product.setCategory(categoryFromDatabase);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
