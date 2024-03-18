package dev.debayan.productservice.Repositories;

import dev.debayan.productservice.modals.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {



    Category findByTitle(String title);
}
