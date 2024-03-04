package dev.debayan.productservice.dtos;

import dev.debayan.productservice.modals.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {
    private  Long id ;
    private String title;

    public Category toCategory() {
        Category category = new Category();
       // category.setId(id);
        category.setTitle(title);

        return  category;
    }
}
