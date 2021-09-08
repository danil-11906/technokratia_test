package technokratos.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import technokratos.demo.domain.entity.Product;
import technokratos.demo.domain.entity.Type;
import technokratos.demo.service.TypeService;

@Controller
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("addType")
    public String getAddProductPage() {
        return "add_type";
    }

    @PostMapping("addType")
    public String addProduct(Type form) {
        typeService.addType(form);
        return "redirect:/list";
    }
}
