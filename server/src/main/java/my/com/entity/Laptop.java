package my.com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import my.com.entity.base.Product;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Laptop extends Product {

    private String ram;
    private String hdd;
    private String protsessor;
    private String prSpeed;
    private String screen;

}
