package sofm.repo;

import sofm.entity.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
  List<Product> findAllByCategory_Id(Long categoryId);
  default List<Product> findAllOrderByPriceAsc() {
    return findAll(Sort.by(Sort.Direction.ASC, "price"));
  }
}