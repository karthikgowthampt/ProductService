package dev.karthik.productservicemyimplementaion.controllers;

import dev.karthik.productservicemyimplementaion.dtos.CreateProductRequestDto;
import dev.karthik.productservicemyimplementaion.dtos.UpdateProductRequestDto;
import dev.karthik.productservicemyimplementaion.models.Category;
import dev.karthik.productservicemyimplementaion.models.Product;
import dev.karthik.productservicemyimplementaion.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;

    public ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate){
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId){
        return productService.getSingleProduct(productId);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto request){
        return productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()
        );
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long productId){
        System.out.println("Deleting Product: "+productId);
        productService.deleteProduct(productId);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getSpecificCategory(@PathVariable("category") String category){
        return productService.getSpecificCategory(category);
    }

    @GetMapping("/products/category")
    public List<String> getAllCategory() {
        return productService.getAllCategory();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long productId,
                                 @RequestBody UpdateProductRequestDto updateRequest) {

        Product request = new Product();
        request.setId(productId);
        request.setTitle(updateRequest.getTitle());
        request.setDescription(updateRequest.getDescription());
        Category temp = new Category();
        temp.setTitle(updateRequest.getCategory());
        request.setCategory(temp);
        request.setPrice(updateRequest.getPrice());
        request.setImageUrl(updateRequest.getImage());

        return productService.updateProduct(productId, request);
    }
}
