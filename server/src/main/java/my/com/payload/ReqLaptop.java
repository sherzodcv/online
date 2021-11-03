package my.com.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqLaptop {

    private UUID id;
    private String ram;
    private String hdd;
    private String protsessor;
    private String prSpeed;
    private String screen;
    private String model;
    private String price;
    private String color;
    private String imageId;
}
