package technokratos.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import technokratos.demo.domain.entity.Product;
import technokratos.demo.domain.entity.Type;
import technokratos.demo.domain.enums.StateHave;
import technokratos.demo.service.ProductService;
import technokratos.demo.service.TypeService;
import technokratos.demo.util.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/list")
    public String getUsersPage(Model model) {
        model.addAttribute("productList", productService.getAllProduct());
        return "list";
    }

    @GetMapping("addProduct")
    public String getAddProductPage(Model model) {
        model.addAttribute("types", typeService.getAllTypes());
        return "add_product";
    }

    @PostMapping("addProduct")
    public String addProduct(@RequestParam("changed") String selected,
                             Product form) {
        String[] checked = selected.split(",");
        if (checked.length == 1) {
            List<Type> typeList = typeService.getAllTypes();
            for (Type types : typeList) {
                for (String string : checked) {
                    if (string.equals(types.getName())) {
                    productService.addProduct(form, types);
                    }
                }
            }
        }

        return "redirect:/list";
    }

    @GetMapping("stateHave")
    public String getStateProductPage(Model model) {
        model.addAttribute("product", productService.getAllProduct());
        return "update_product";
    }

    @PostMapping("stateHave")
    public String updateProduct(@RequestParam("selected") String selected,
                                @RequestParam("have") String haved) {
        String[] checked = selected.split(",");
        String[] have = haved.split(",");
        if (have.length==1) {
            StateHave del = StateHave.DELETE;
            StateHave hav = StateHave.HAVE;
            List<Product> productList = productService.getAllProduct();
            for (Product product : productList) {
                for (String string : checked) {
                    if (string.equals(product.getName())) {
                        if (del.toString().equals(have[0])) {
                            productService.updateStateHave(product, del);
                        } else {
                            productService.updateStateHave(product, hav);
                        }
                    }
                }
            }
        }

        return "redirect:/list";
    }

    @GetMapping("/")
    public String getStartPage(Model model) {
        return "redirect:/list";
    }

}
