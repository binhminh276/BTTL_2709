package sofm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sofm.entity.Category;
import sofm.entity.User;
import sofm.repo.CategoryRepo;
import sofm.repo.UserRepo;
import sofm.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User create(User u, Set<Long> categoryIds) {
        if (categoryIds != null) {
            Set<Category> cats = new HashSet<>(categoryRepo.findAllById(categoryIds));
            u.setCategories(cats);
        }
        return userRepo.save(u);
    }

    @Override
    public User update(Long id, User u, Set<Long> categoryIds) {
        User db = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
        db.setFullname(u.getFullname());
        db.setEmail(u.getEmail());
        db.setPassword(u.getPassword());
        db.setPhone(u.getPhone());
        if (categoryIds != null) {
            Set<Category> cats = new HashSet<>(categoryRepo.findAllById(categoryIds));
            db.setCategories(cats);
        }
        return userRepo.save(db);
    }

    @Override
    public boolean delete(Long id) {
        if (!userRepo.existsById(id)) return false;
        userRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}