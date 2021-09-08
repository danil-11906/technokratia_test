package technokratos.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import technokratos.demo.domain.entity.Admin;
import technokratos.demo.domain.enums.StateRole;
import technokratos.demo.repository.AdminRepository;

@Component
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(Admin form) {
        Admin newUser = Admin.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .role(StateRole.ADMIN).build();

        adminRepository.save(newUser);
    }
}
