import java.util.List;

public class Expense {
    private SplitStrategy splitStrategy;
    Expense(SplitStrategy splitStrategy){
        this.splitStrategy = splitStrategy;
    }

    public void process(User payer, List<User> payees, double amount, List<String> remainingArgs) {
       splitStrategy.split(payer,payees, amount, remainingArgs);
    }
}
