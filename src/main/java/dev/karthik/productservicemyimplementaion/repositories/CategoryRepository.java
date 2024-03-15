package dev.karthik.productservicemyimplementaion.repositories;

import dev.karthik.productservicemyimplementaion.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);

    Category save(Category category);
}
