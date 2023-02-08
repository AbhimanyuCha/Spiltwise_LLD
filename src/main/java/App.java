import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class App {
    private static DisplayManager displayManager;
    private static ExpenseManager expenseManager;
    private static UserDAO userRepo;
    private static final String DELIMITTER = " ";
    private static final String SHOW_TYPE_REQUEST = "SHOW";
    private static final String EXPENSE_TYPE_REQUEST = "EXPENSE";

    public App(){
        displayManager = DisplayManager.getInstance();
        expenseManager = ExpenseManager.getInstance();
        userRepo = UserDAO.getInstance();
    }

    private void processExpense(String ...args){
        User payer = userRepo.getUser(args[1]);
        double amount = Double.parseDouble(args[2]);
        int countOfPayees = Integer.parseInt(args[3]);
        List<User> payees = new ArrayList<>();
        for(int i = 0 ; i < countOfPayees ; i++){
            User tmp_user = userRepo.getUser(args[i + 4]);
            payees.add(tmp_user);
        }

        List<String> remainingArgs = new ArrayList<>();
        for(int j = 4+countOfPayees ; j < args.length ; j++) remainingArgs.add(args[j]);
        expenseManager.expense(payer, payees, amount, remainingArgs);
    }

    public void parseRequest(String _args){
        String[] args = _args.split(DELIMITTER);
        if(args[0].equals(SHOW_TYPE_REQUEST))
            processShow(args);
        else if(args[0].equals(EXPENSE_TYPE_REQUEST))
            processExpense(args);
        else
            throw new RuntimeException("Invalid Request !");
    }

    private void processShow(String[] args) {
        if(args.length == 2)
            displayManager.showUser(userRepo.getUser(args[1]));
        else
            displayManager.showAll();
        System.out.println();
    }

    public static void main(String[] args) {
        App app = new App();
        app.parseRequest("EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL");
        app.parseRequest("SHOW u1");
        app.parseRequest("SHOW u4");
        app.parseRequest("EXPENSE u1 1250 2 u2 u3 EXACT 370 880");
        app.parseRequest("SHOW");
        app.parseRequest("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20");
        app.parseRequest("SHOW u1");
    }

}
