package technokratos.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import technokratos.demo.domain.entity.Admin;
import technokratos.demo.service.AdminService;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signup_page";
    }

    @PostMapping("/signUp")
    public String signUp(Admin form) {
        adminService.signUp(form);
        return "redirect:/signIn";
    }

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "sign_in_page";
    }

}
