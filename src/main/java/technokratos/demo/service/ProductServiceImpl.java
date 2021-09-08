package technokratos.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import technokratos.demo.domain.entity.Product;
import technokratos.demo.domain.entity.Type;
import technokratos.demo.domain.enums.StateHave;
import technokratos.demo.repository.ProductRepository;
import technokratos.demo.repository.TypeRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductForOrder(){
        List<Product> productList = productRepository.findAll();
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getStateHave()==StateHave.HAVE){
                list.add(productList.get(i));
            }
        }
        return list;
    }


    @Override
    public void addProduct(Product form, Type type) {
        Product newProduct = Product.builder()
                .name(form.getName())
                .cost(form.getCost())
                .type(type)
                .stateHave(StateHave.HAVE)
                .build();
        productRepository.save(newProduct);
    }

    @Override
    public void updateStateHave(Product product, StateHave stateHave) {
        Product productOld = productRepository.findFirstByName(product.getName());
        Product productNew = Product.builder()
                .id(productOld.getId())
                .name(productOld.getName())
                .cost(productOld.getCost())
                .type(productOld.getType())
                .stateHave(stateHave)
                .build();
        productRepository.save(productNew);
    }

}
