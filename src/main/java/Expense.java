import java.util.List;
import java.util.Map;

public class Expense {
    private SplitStrategy splitStrategy;
    Expense(SplitStrategy splitStrategy){
        this.splitStrategy = splitStrategy;
    }

    public void process(User payer, List<User> payees, double amount, List<String> remainingArgs) {
       splitStrategy.split(payer,payees, amount, remainingArgs);
    }
}
