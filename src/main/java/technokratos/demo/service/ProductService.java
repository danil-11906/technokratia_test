package technokratos.demo.service;

import technokratos.demo.domain.entity.Product;
import technokratos.demo.domain.entity.Type;
import technokratos.demo.domain.enums.StateHave;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    List<Product> getAllProductForOrder();

    void addProduct(Product form, Type type);

    void updateStateHave(Product product, StateHave stateHave);
}
