package sofm.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private Integer quantity;
  @Column(length = 2000)
  private String desc;
  private Double price;

  // FK đến User (userid)
  @ManyToOne @JoinColumn(name="user_id")
  private User user;

  // thêm FK đến Category để query theo category
  @ManyToOne @JoinColumn(name="category_id")
  private Category category;
}
