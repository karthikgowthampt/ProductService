package dev.karthik.productservicemyimplementaion.services;

import dev.karthik.productservicemyimplementaion.models.Category;
import dev.karthik.productservicemyimplementaion.models.Product;
import dev.karthik.productservicemyimplementaion.repositories.CategoryRepository;
import dev.karthik.productservicemyimplementaion.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public Product createProduct(String title, String description, String category,
                                 double price, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        Category categoryFromDatabase = categoryRepository.findByTitle(category);


        if (categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setTitle(category);
            categoryFromDatabase = newCategory;
        }

        product.setCategory(categoryFromDatabase);

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public List<Product> getSpecificCategory(String category) {
        return null;
    }

    @Override
    public List<String> getAllCategory() {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}

