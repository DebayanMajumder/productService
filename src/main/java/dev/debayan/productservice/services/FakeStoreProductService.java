package dev.debayan.productservice.services;

import dev.debayan.productservice.Exception.ProductNotFoundException;
import dev.debayan.productservice.dtos.CategoryRequestDto;
import dev.debayan.productservice.dtos.FakeStoreProductDto;
import dev.debayan.productservice.modals.Category;
import dev.debayan.productservice.modals.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import dev.debayan.productservice.Exception.ProductNotFoundException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service("FakestoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    public  FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long ProductId) throws ProductNotFoundException {
       FakeStoreProductDto fakeStoreProduct =  restTemplate.getForObject("https://fakestoreapi.com/products/" + ProductId,
                FakeStoreProductDto.class);
        return fakeStoreProduct.toProduct();
    }

    @Override
    public Product deleteProductDetails(Long ProductId) {
        FakeStoreProductDto fakeStoreProduct =  restTemplate.getForObject("https://fakestoreapi.com/products/" + ProductId,
                FakeStoreProductDto.class);
        return fakeStoreProduct.toProduct();
    }

    @Override
    public List<Product>  getProductDetailsCategory(String Category) {
       // FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/category/" + Category,
        //        FakeStoreProductDto.class);
        FakeStoreProductDto[] fakeStoreProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + Category,
                        FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProduct: fakeStoreProducts) {
            products.add(fakeStoreProduct.toProduct());
        }
        return products;
    }

    @Override
    public List<String> getAllCategories() {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, String title, String description, String category, double price, String image) throws  ProductNotFoundException{
        return null;
    }

//    @Override
//    public List<Category> getAllCategories() {
//        CategoryRequestDto[] categoryRequests = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/categories",
//                CategoryRequestDto[].class);
//        List<Category> categories = new ArrayList<>();
//        for (CategoryRequestDto categoryRequest: categoryRequests) {
//            categories.add(categoryRequest.toCategory());
//        }
//        return categories;
//
//    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProduct: fakeStoreProducts) {
            products.add(fakeStoreProduct.toProduct());
        }
        return products;
       // return null;
    }



    @Override
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
       FakeStoreProductDto response =  restTemplate.postForObject(
               "https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class

        );



        return response.toProduct();
    }

    @Override
    public Product updateProduct(Long ProductId,
                                 String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) throws ProductNotFoundException{

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
       // fakeStoreProductDto.setId(ProductId);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products/" + ProductId,
                fakeStoreProductDto,
                FakeStoreProductDto.class);

        return response.toProduct();

    }
}
