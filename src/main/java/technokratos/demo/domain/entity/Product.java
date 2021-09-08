package technokratos.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import technokratos.demo.domain.enums.StateHave;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cost;

    @Enumerated(value = EnumType.STRING)
    private StateHave stateHave;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;
}
