package sofm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sofm.entity.Category;
import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
  // Lấy tất cả category mà 1 user đang tham gia (many-to-many)
  List<Category> findAllByUsers_Id(Long userId);

  // Tìm theo tên (không phân biệt hoa/thường)
  List<Category> findByNameContainingIgnoreCase(String name);
}
