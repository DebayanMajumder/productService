package dev.debayan.productservice.services;

import dev.debayan.productservice.dtos.CreateProductRequestDto;
import dev.debayan.productservice.modals.Category;
import dev.debayan.productservice.modals.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long ProductId) ;

    Product deleteProductDetails(Long ProductId);

   List<Product>  getProductDetailsCategory(String Category);

   List<Category> getAllCategories();

    List<Product> getAllProducts();

    Product createProduct(String title,
                          String description,
                          String category,
                          double price,
                          String image);

    Product updateProduct(Long ProductId,
                          String title,
                          String description,
                          String category,
                          double price,
                          String image);
}
