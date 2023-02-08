import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class User {
    private String name;
    private String email;
    private String id;
    private Map<User, Double> amountOwedBy;
    User(String name, String email, String id){
        this.email = email;
        amountOwedBy = new HashMap<>();
        this.name = name;
        this.id = id;
    }
}
