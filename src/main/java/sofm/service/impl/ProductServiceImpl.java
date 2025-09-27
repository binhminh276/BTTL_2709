package sofm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sofm.entity.Category;
import sofm.entity.Product;
import sofm.entity.User;
import sofm.repo.CategoryRepo;
import sofm.repo.ProductRepo;
import sofm.repo.UserRepo;
import sofm.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllOrderByPriceAsc() {
        // dùng default method hoặc derived query tùy bạn đã khai báo
        return productRepo.findAllOrderByPriceAsc();
        // hoặc: return productRepo.findAllByOrderByPriceAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllByCategoryId(Long categoryId) {
        return productRepo.findAllByCategory_Id(categoryId);
    }

    @Override
    public Product create(Product p, Long userId, Long categoryId) {
        if (userId != null) {
            User u = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
            p.setUser(u);
        } else {
            p.setUser(null);
        }
        if (categoryId != null) {
            Category c = categoryRepo.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId));
            p.setCategory(c);
        } else {
            p.setCategory(null);
        }
        return productRepo.save(p);
    }

    @Override
    public Product update(Long id, Product p, Long userId, Long categoryId) {
        Product db = productRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        db.setTitle(p.getTitle());
        db.setQuantity(p.getQuantity());
        db.setDesc(p.getDesc());
        db.setPrice(p.getPrice());

        if (userId != null) {
            User u = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
            db.setUser(u);
        }
        if (categoryId != null) {
            Category c = categoryRepo.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId));
            db.setCategory(c);
        }
        return productRepo.save(db);
    }

    @Override
    public boolean delete(Long id) {
        if (!productRepo.existsById(id)) return false;
        productRepo.deleteById(id);
        return true;
    }
}