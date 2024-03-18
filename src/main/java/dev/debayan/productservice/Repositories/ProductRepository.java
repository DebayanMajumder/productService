package dev.debayan.productservice.Repositories;

import dev.debayan.productservice.modals.Category;
import dev.debayan.productservice.modals.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product  save(Product p);

    @Override
    List<Product> findAll();


    Product findByIdEquals(Long id);

    Product deleteProductByIdEquals(Long Id);

    List<Product>findAllByTitle(String title);

 //   List<Product> getProductsByCategory(String category);

}
