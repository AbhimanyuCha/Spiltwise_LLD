import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ExactSplit implements SplitStrategy{
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

        for(int i = 0 ; i < payees.size() ; i++) {
            double amountForUser = exactAmounts.get(i);
            User u = payees.get(i);

            payer.getAmountOwedBy().putIfAbsent(u , payer.getAmountOwedBy().getOrDefault(u , 0.0));
            u.getAmountOwedBy().putIfAbsent(payer, u.getAmountOwedBy().getOrDefault(payer, 0.0));

            payer.getAmountOwedBy().put(u, payer.getAmountOwedBy().get(u) + amountForUser);
            u.getAmountOwedBy().put(payer, u.getAmountOwedBy().get(payer) - amountForUser);
        }
    }
}
