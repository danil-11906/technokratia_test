package technokratos.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import technokratos.demo.domain.entity.Orders;
import technokratos.demo.domain.entity.Product;
import technokratos.demo.domain.entity.Type;
import technokratos.demo.repository.OrderRepository;
import technokratos.demo.repository.ProductRepository;
import technokratos.demo.repository.TypeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public void addOrder(List<Product> product, Orders order) {
        Date date = new Date();
        Orders newOrder = Orders.builder()
                .date(date)
                .email(order.getEmail())
                .products(product)
                .hashCode(date.hashCode())
                .build();
        orderRepository.save(newOrder);
    }

    @Override
    public List<String> getOrdersOfEmail(String email) {
        List<Orders> ordersList = orderRepository.findAllByEmail(email);
        List<String> finalList = new ArrayList<String>();
        for (int i = 0; i < ordersList.size(); i++) {
            finalList.add(String.valueOf(ordersList.get(i).getHashCode()));
        }
        return finalList;
    }

    @Override
    public List<String> getOrdersOfType(Type form) {
        System.out.println("1");
        List<String> list = new ArrayList<String>();
        Type type = typeRepository.findFirstByName(form.getName());
        List<Product> products = productRepository.findAll();
        List<Product> finalProduct = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getType().getId().equals(type.getId())) {
                finalProduct.add(products.get(i));
            }
        }
        int q =0;
        List<Orders> orders = orderRepository.findAll();
        for (int i = 0; i < orders.size(); i++) {
            List<Product> orderProducts = orders.get(i).getProducts();
            for (int j = 0; j < orderProducts.size(); j++) {
                for (int k = 0; k < finalProduct.size(); k++) {
                    if (orderProducts.get(j).getId().equals(finalProduct.get(k).getId())){
                        list.add(String.valueOf(orders.get(i).getHashCode()));
                        q++;
                        break;
                    }
                }
                if (q == 1) {
                    break;
                }
            }
            q = 0;
        }
        return list;
    }

    @Override
    public List<String> getOrdersOfDate(Date in, Date out){
        List<Orders> orders = orderRepository.findAll();
        List<String> ordersList = new ArrayList<String>();
        for (int i = 0; i < orders.size(); i++) {
            if ((orders.get(i).getDate().after(in))&&(orders.get(i).getDate().before(out))) {
                ordersList.add(String.valueOf(orders.get(i).getHashCode()));
            }
        }
        return ordersList;
    }


}
