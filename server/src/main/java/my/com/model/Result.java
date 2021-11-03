package my.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String message;
    private boolean success;
    private Object data;


    public Result(String message, boolean success) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }
}
