package my.com.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import my.com.entity.base.Product;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pc extends Product {

    private String ram;
    private String hdd;
    private String protsessor;
    private String prSpeed;


}
