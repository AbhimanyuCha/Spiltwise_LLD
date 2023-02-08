import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDAO {
    Map<String, User> users;
    private static UserDAO instance = null;

    private UserDAO(){
        users = new HashMap<>();
    }
    public static UserDAO getInstance(){
        if(instance == null)
            instance = new UserDAO();
        return instance;
    }
    public void addUser(String id, String email, String name){
        if(users.containsKey(id))
            return;
        users.put(id, new User(name, email, id));
    }
    public User getUser(String id){
        if(!users.containsKey(id))
            addUser(id, " ", id);
        return users.get(id);
    }

    public List<User> getAllUsers(){
        return users.entrySet().stream().map(stringUserEntry -> stringUserEntry.getValue()).collect(Collectors.toList());
    }
}
