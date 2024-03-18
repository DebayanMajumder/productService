package dev.debayan.productservice.modals;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel{

    private  String title;
    private  String description;
    private  double price;
    private  String imageUrl;
@ManyToOne(cascade = {CascadeType.PERSIST})
    private  Category category;


}
