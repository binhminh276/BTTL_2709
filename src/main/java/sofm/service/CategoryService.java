package sofm.service;

import sofm.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category create(Category c);
    Category update(Long id, Category c);
    boolean delete(Long id);
}