package sofm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sofm.entity.User;
import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
  // Lấy tất cả user thuộc 1 category (many-to-many)
  List<User> findAllByCategories_Id(Long categoryId);

  // Check trùng email khi CRUD
  boolean existsByEmail(String email);
}