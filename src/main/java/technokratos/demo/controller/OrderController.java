package technokratos.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import technokratos.demo.domain.entity.Orders;
import technokratos.demo.domain.entity.Product;
import technokratos.demo.domain.entity.Type;
import technokratos.demo.dto.DatePeriod;
import technokratos.demo.repository.OrderRepository;
import technokratos.demo.service.OrderService;
import technokratos.demo.service.ProductService;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("bag")
    public String getBagPage(Model model) {
        model.addAttribute("products", productService.getAllProductForOrder());
        return "bag_page";
    }

    @PostMapping("bag")
    public String addOrder(@RequestParam("selected") String selected,
                           Orders order) {
        String[] checked = selected.split(",");
        List<Product> productList = productService.getAllProduct();
        List<Product> productOrder = new ArrayList<Product>();
        for (Product product : productList) {
            for (String string : checked) {
                if (string.equals(product.getName())) {
                    productOrder.add(product);
                }
            }
        }
        orderService.addOrder(productOrder, order);
        return "complete_page";
    }

    @GetMapping("/searchEmail")
    public String getSearchPage(){
        return "get_info_page";
    }

    @PostMapping("/emailOrders")
    public String getOrdersOfEmail(Orders order, Model model){
        if (orderService.getOrdersOfEmail(order.getEmail()).size()>0) {
            List<String> ordersList = orderService.getOrdersOfEmail(order.getEmail());
            model.addAttribute("hashOrders", ordersList);
            return "email_orders";
        }
        else return "redirect:/searchEmail";
    }

    @GetMapping("/searchType")
    public String getSearchPageOfType(){
        return "type_info_page";
    }

    @PostMapping("/typeOrders")
    public String getOrdersOfEmail(Type type, Model model){
            List<String> ordersList = orderService.getOrdersOfType(type);
            model.addAttribute("hashOrders", ordersList);
            return "email_orders";
    }

    @GetMapping("/searchDate")
    public String getSearchPageOfDate(){
        return "date_info_page";
    }

    @PostMapping("/dateOrders")
    public String getOrdersOfDate(@RequestParam("dateIn")
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateIn,
                                  @RequestParam("dateOut")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOut,
                                  Model model){
        List<String> ordersList = orderService.getOrdersOfDate(dateIn, dateOut);
        model.addAttribute("hashOrders", ordersList);
        return "email_orders";
    }
}
