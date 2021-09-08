package technokratos.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import technokratos.demo.domain.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findFirstByEmail(String email);
}
