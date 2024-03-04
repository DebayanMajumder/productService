package dev.debayan.productservice.controllers;

import dev.debayan.productservice.dtos.CreateProductRequestDto;
import dev.debayan.productservice.dtos.UpdateProductRequestDTO;
import dev.debayan.productservice.modals.Category;
import dev.debayan.productservice.modals.Product;
import dev.debayan.productservice.services.FakeStoreProductService;
import dev.debayan.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService ;

    public  ProductController(ProductService productService){
        this.productService = productService;
    }
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
    public Product getProductDetails(@PathVariable("id") Long ProductId) {
        return  productService.getSingleProduct((ProductId));
    }

    @GetMapping("products/category/{category}")
    public List<Product>  getProductDetailsSpecificCategory(@PathVariable("category") String Category) {
        return productService.getProductDetailsCategory(Category);
    }

    @GetMapping("products/categories")
    public  List<Category> getAllCategoryDetails() {
        return  productService.getAllCategories();
    }

    @DeleteMapping("/products/{id}")
    public  Product deleteProductDetails(@PathVariable("id") Long ProductId) {
        return  productService.deleteProductDetails(ProductId);
    }

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @PutMapping("/product/{id}")
    public  Product  updateProduct(@PathVariable("id") Long ProductId, @RequestBody UpdateProductRequestDTO request) {
        return productService.updateProduct(
                ProductId,
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()

        ) ;
    }


}
