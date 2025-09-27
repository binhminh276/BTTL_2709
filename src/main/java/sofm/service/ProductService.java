package sofm.service;

import sofm.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    List<Product> findAllOrderByPriceAsc();
    List<Product> findAllByCategoryId(Long categoryId);

    Product create(Product p, Long userId, Long categoryId);
    Product update(Long id, Product p, Long userId, Long categoryId);
    boolean delete(Long id);
}