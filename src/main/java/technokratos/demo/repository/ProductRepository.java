package technokratos.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import technokratos.demo.domain.entity.Product;
import technokratos.demo.domain.entity.Type;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByName(String name);
}
