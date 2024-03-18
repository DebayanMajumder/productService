package dev.debayan.productservice.controllers;

import dev.debayan.productservice.Exception.ProductNotFoundException;
import dev.debayan.productservice.dtos.CreateProductRequestDto;
import dev.debayan.productservice.dtos.UpdateProductRequestDTO;
import dev.debayan.productservice.modals.Category;
import dev.debayan.productservice.modals.Product;
import dev.debayan.productservice.services.FakeStoreProductService;
import dev.debayan.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {



    private ProductService productService ;

    private  RestTemplate restTemplate;

    public  ProductController(@Qualifier("SelfProductService") ProductService productService, RestTemplate restTemplate){
        this.productService = productService;
        this.restTemplate = restTemplate;
    }
//    public  ProductController(RestTemplate restTemplate){
//        this.restTemplate = restTemplate;
//    }

    // Post/ products
    // request body
    // {
    //      title:
    //      description:
    //      price:
    // }
    @PostMapping("/products")
    public  Product createProduct(@RequestBody CreateProductRequestDto request) {

        return  productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()
        );
    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long ProductId) throws Exception {
        return  productService.getSingleProduct((ProductId));
    }

    @GetMapping("products/category/{category}")
    public List<Product>  getProductDetailsSpecificCategory(@PathVariable("category") String Category) {
        return productService.getProductDetailsCategory(Category);
    }

//    @GetMapping("products/categories")
//    public  List<Category> getAllCategoryDetails() {
//        return  productService.getAllCategories();
//    }

    @GetMapping("/products/categories")
    public String[] getAllCategories()
    {
        return restTemplate.getForObject("https://fakestoreapi.com/products/categories" , String[].class);
    }

    @DeleteMapping("/products/{id}")
    public  Product deleteProductDetails(@PathVariable("id") Long ProductId) {
        return  productService.deleteProductDetails(ProductId);
    }

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @PutMapping("/products/{id}")
    public  Product  updateProduct(@PathVariable("id") Long ProductId, @RequestBody UpdateProductRequestDTO request) throws  Exception{
        return productService.updateProduct(
                ProductId,
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()

        ) ;
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<Product> updateProducts(@PathVariable("id") Long id, @RequestBody UpdateProductRequestDTO updateRequest) throws Exception {
        Product updateProductResponse = productService.updateProduct(id,
                updateRequest.getTitle(),
                updateRequest.getDescription(),
                updateRequest.getCategory(),
                updateRequest.getPrice(),
                updateRequest.getImage()
        );

        return new ResponseEntity<>(updateProductResponse, HttpStatus.OK);
    }


}
