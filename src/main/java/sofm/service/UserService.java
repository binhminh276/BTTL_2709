package sofm.service;

import sofm.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User create(User u, Set<Long> categoryIds);
    User update(Long id, User u, Set<Long> categoryIds);
    boolean delete(Long id);
    boolean existsByEmail(String email);
}