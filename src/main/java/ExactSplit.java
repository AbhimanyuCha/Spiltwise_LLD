import java.util.ArrayList;
import java.util.List;

public class ExactSplit extends SplitStrategy{
    public void split(User payer, List<User> payees, double amount,
                      List<String> args) {
        List<Double> exactAmounts = new ArrayList<>();
        for(int i = 1 ; i < args.size() ; i++){
            double val = Double.parseDouble(args.get(i));
            exactAmounts.add(val);
            amount -= val;
        }

        if(amount != 0)
            throw new RuntimeException("The values dont sum up to the given amount paid by payer");

        if(exactAmounts.size() != payees.size())
            throw new RuntimeException("The exact amounts is not provided for all users !");

        distribute(payer, payees, exactAmounts);
    }
}
