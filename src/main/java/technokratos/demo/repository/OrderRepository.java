package technokratos.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import technokratos.demo.domain.entity.Orders;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByEmail(String email);
}
