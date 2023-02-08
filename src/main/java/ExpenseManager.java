import java.util.ArrayList;
import java.util.List;


public class ExpenseManager {
    private List<Expense> history;
    private static ExpenseManager instance = null;
    UserDAO userRepo;
    private ExpenseManager(){
        userRepo = UserDAO.getInstance();
        history = new ArrayList<>();
    }

    public static ExpenseManager getInstance(){
        if(instance == null)
            instance = new ExpenseManager();
        return instance;
    }

    private SplitStrategy getSplitStrategy(String arg){
        ExpenseType expenseType = ExpenseType.valueOf(arg);
        switch (expenseType){
            case EQUAL:
                return new EqualSplit();
            case EXACT:
                return new ExactSplit();
            case PERCENT:
                return new PercentSplit();
        }
        throw new RuntimeException("Split Strategy not found");
    }

    public void expense(User payer, List<User> payees, double amount, List<String> remainingArgs) {
        SplitStrategy splitStrategy = getSplitStrategy(remainingArgs.get(0));
        Expense expense = new Expense(splitStrategy);
        expense.process(payer, payees, amount, remainingArgs);
        history.add(expense);
    }

}
