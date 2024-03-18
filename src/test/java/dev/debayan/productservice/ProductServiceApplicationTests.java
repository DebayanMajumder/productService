package dev.debayan.productservice;

import dev.debayan.productservice.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {
@Autowired
    private ProductRepository productRepository;
    @Test
    void contextLoads() {
    }

    @Test
    void testQueries() {
        productRepository.findAllByTitle("hello");
    }
}
