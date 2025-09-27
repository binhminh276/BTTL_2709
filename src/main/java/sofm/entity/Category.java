package sofm.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Category {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String images;

  @ManyToMany(mappedBy = "categories")
  private Set<User> users;
}