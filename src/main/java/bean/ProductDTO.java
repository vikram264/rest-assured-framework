package bean;

import lombok.*;

@Getter
@Setter
@ToString
public class ProductDTO {
    Integer id;
    String name;
    Long price;
    String status;
}
