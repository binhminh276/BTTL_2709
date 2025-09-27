package sofm.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import sofm.entity.Category;
import sofm.entity.Product;
import sofm.entity.User;
import sofm.service.CategoryService;
import sofm.service.ProductService;
import sofm.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ShopGraphQL {

    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    /* ------------ Query ------------ */
    @QueryMapping
    public List<Product> productsSortedByPrice() {
        return productService.findAllOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productService.findAllByCategoryId(categoryId);
    }

    @QueryMapping public List<Product> products() { return productService.findAll(); }
    @QueryMapping public Product product(@Argument Long id) { return productService.findById(id).orElse(null); }

    @QueryMapping public List<User> users() { return userService.findAll(); }
    @QueryMapping public User user(@Argument Long id) { return userService.findById(id).orElse(null); }

    @QueryMapping public List<Category> categories() { return categoryService.findAll(); }
    @QueryMapping public Category category(@Argument Long id) { return categoryService.findById(id).orElse(null); }

    /* ------------ Mutation: User ------------ */
    public record UserInput(String fullname, String email, String password, String phone, List<Long> categoryIds) {}

    @MutationMapping
    public User createUser(@Argument UserInput input) {
        User u = new User();
        u.setFullname(input.fullname());
        u.setEmail(input.email());
        u.setPassword(input.password());
        u.setPhone(input.phone());
        return userService.create(u, input.categoryIds()==null? null: Set.copyOf(input.categoryIds()));
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput input) {
        User u = new User();
        u.setFullname(input.fullname());
        u.setEmail(input.email());
        u.setPassword(input.password());
        u.setPhone(input.phone());
        return userService.update(id, u, input.categoryIds()==null? null: Set.copyOf(input.categoryIds()));
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        return userService.delete(id);
    }

    /* ------------ Mutation: Category ------------ */
    public record CategoryInput(String name, String images) {}

    @MutationMapping
    public Category createCategory(@Argument CategoryInput input) {
        Category c = new Category();
        c.setName(input.name());
        c.setImages(input.images());
        return categoryService.create(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument CategoryInput input) {
        Category c = new Category();
        c.setName(input.name());
        c.setImages(input.images());
        return categoryService.update(id, c);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        return categoryService.delete(id);
    }

    /* ------------ Mutation: Product ------------ */
    public record ProductInput(String title, Integer quantity, String desc, Double price, Long userId, Long categoryId) {}

    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {
        Product p = new Product();
        p.setTitle(input.title());
        p.setQuantity(input.quantity());
        p.setDesc(input.desc());
        p.setPrice(input.price());
        return productService.create(p, input.userId(), input.categoryId());
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput input) {
        Product p = new Product();
        p.setTitle(input.title());
        p.setQuantity(input.quantity());
        p.setDesc(input.desc());
        p.setPrice(input.price());
        return productService.update(id, p, input.userId(), input.categoryId());
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        return productService.delete(id);
    }
}