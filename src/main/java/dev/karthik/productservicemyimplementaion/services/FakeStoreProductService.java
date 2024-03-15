package dev.karthik.productservicemyimplementaion.services;

import dev.karthik.productservicemyimplementaion.dtos.FakeStoreProductDto;
import dev.karthik.productservicemyimplementaion.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);
        assert fakeStoreProduct != null;
        return fakeStoreProduct.toProduct();
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setDescription(description);

        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class
        );

        if (response == null) return new Product();

        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> temp = new ArrayList<>();
        for (FakeStoreProductDto dto: response){
            temp.add(dto.toProduct());
        }
        return temp;
    }
    @Override
    public void deleteProduct(Long productId) {
        System.out.println("Deleting");
        restTemplate.delete(
                "https://fakestoreapi.com/products/" + productId);
    }
    @Override
    public List<Product> getSpecificCategory(String category){
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/"+category,
                FakeStoreProductDto[].class);
        List<Product> temp = new ArrayList<>();
        for (FakeStoreProductDto dto: response){
            temp.add(dto.toProduct());
        }
        return temp;
    }
    @Override
    public List<String> getAllCategory(){
        List<String> response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",List.class);
        return response;
    }
    @Override
    public Product updateProduct(Long id, Product product){
        FakeStoreProductDto updatedResponse = new FakeStoreProductDto();
        updatedResponse.setId(product.getId());
        updatedResponse.setTitle(product.getTitle());
        updatedResponse.setDescription(product.getDescription());
        updatedResponse.setPrice(product.getPrice());
        updatedResponse.setImage(product.getImageUrl());
        updatedResponse.setCategory(product.getCategory().getTitle());
        restTemplate.put(
                "https://fakestoreapi.com/products/" + id,
                updatedResponse);

        return  updatedResponse.toProduct();
    }
}
