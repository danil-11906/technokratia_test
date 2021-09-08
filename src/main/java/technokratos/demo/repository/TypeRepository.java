package technokratos.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import technokratos.demo.domain.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findFirstByName(String name);
}
