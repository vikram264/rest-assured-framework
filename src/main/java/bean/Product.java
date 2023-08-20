package bean;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Builder
@Data
@ToString
public class Product {
    Integer id;
    String name;
    Long price;
}
