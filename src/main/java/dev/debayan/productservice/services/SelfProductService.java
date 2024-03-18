package dev.debayan.productservice.services;

import dev.debayan.productservice.Exception.ProductNotFoundException;
import dev.debayan.productservice.Repositories.CategoryRepository;
import dev.debayan.productservice.Repositories.ProductRepository;
import dev.debayan.productservice.modals.Category;
import dev.debayan.productservice.modals.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SelfProductService")
public class SelfProductService implements  ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public  SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long ProductId) throws  ProductNotFoundException {

        return productRepository.findByIdEquals(ProductId) ;
    }

    @Override
    public Product deleteProductDetails(Long ProductId) {
        return productRepository.deleteProductByIdEquals(ProductId);
    }

    @Override
    public List<Product> getProductDetailsCategory(String Category) {
        return null;
    }

    @Override
    public List<String> getAllCategories() {
        List<Category> allCategoriesFromDatabase = categoryRepository.findAll();
        List<String> allCategories = new ArrayList<>();
        for(Category category : allCategoriesFromDatabase){
            allCategories.add(category.getTitle());
        }
        return allCategories;
    }

    @Override
    public Product replaceProduct(Long id, String title, String description, String category, double price, String image) throws  ProductNotFoundException{
        Product existingProduct = getSingleProduct(id);
        existingProduct.setTitle(title);
        existingProduct.setDescription(description);
        existingProduct.setPrice(price);
        existingProduct.setImageUrl(image);
        Category newCategory = new Category();
        newCategory.setTitle(category);
        existingProduct.setCategory(newCategory);

        Product updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, String category, double price, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        Category categoryFromDatabase = categoryRepository.findByTitle(category);
        if(categoryFromDatabase == null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
            categoryFromDatabase = newCategory;
        }

        product.setCategory(categoryFromDatabase);

        Product saveProduct =  productRepository.save(product);
        return  saveProduct;
    }



    @Override
    public Product updateProduct(Long ProductId, String title, String description, String category, double price, String image) throws ProductNotFoundException{
        Product existingProduct = getSingleProduct(ProductId);

        if(title != null) {
            existingProduct.setTitle(title);
        }
        if(description != null){
            existingProduct.setDescription(description);
        }
        if(category != null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
        }
        if(price != 0.0d){
            existingProduct.setPrice(price);
        }
        if(image != null){
            existingProduct.setImageUrl(image);
        }
        Product updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
       // return  null;
    }
}
