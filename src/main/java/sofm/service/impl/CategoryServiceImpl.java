package sofm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sofm.entity.Category;
import sofm.repo.CategoryRepo;
import sofm.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public Category create(Category c) {
        return categoryRepo.save(c);
    }

    @Override
    public Category update(Long id, Category c) {
        Category db = categoryRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));
        db.setName(c.getName());
        db.setImages(c.getImages());
        return categoryRepo.save(db);
    }

    @Override
    public boolean delete(Long id) {
        if (!categoryRepo.existsById(id)) return false;
        categoryRepo.deleteById(id);
        return true;
    }
}