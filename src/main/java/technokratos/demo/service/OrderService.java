package technokratos.demo.service;

import technokratos.demo.domain.entity.Orders;
import technokratos.demo.domain.entity.Product;
import technokratos.demo.domain.entity.Type;

import java.util.Date;
import java.util.List;

public interface OrderService {
    void addOrder(List<Product> product, Orders order);

    List<String> getOrdersOfEmail(String email);

    List<String> getOrdersOfType(Type form);

    List<String> getOrdersOfDate(Date in, Date out);
}
