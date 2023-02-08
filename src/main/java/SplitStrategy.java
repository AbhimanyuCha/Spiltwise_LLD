import java.util.List;

public interface SplitStrategy {
    void split(User payer, List<User> payees, double amount, List<String> args);
}
