package dev.karthik.productservicemyimplementaion.services;

import dev.karthik.productservicemyimplementaion.models.Category;
import dev.karthik.productservicemyimplementaion.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);

    Product createProduct(String title,
                          String description,
                          String category,
                          double price,
                          String image);

    List<Product> getAllProducts();

    void deleteProduct(Long productId);

    List<Product> getSpecificCategory(String category);

    List<String> getAllCategory();

    Product updateProduct(Long id, Product product);

}
