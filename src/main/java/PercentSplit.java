import java.util.ArrayList;
import java.util.List;

public class PercentSplit implements SplitStrategy{
    public void split(User payer, List<User> payees, double amount,
                      List<String> args) {
        List<Double> amt = new ArrayList<>();

        int percentSum = 0;
        for(int i = 1 ; i < args.size() ; i++){
            int percentage = Integer.parseInt(args.get(i));
            amt.add((percentage * amount) / 100);
            percentSum += percentage;
        }

        if(amt.size() != payees.size())
            throw new RuntimeException("The percentage is not provided for all users !");
        if(percentSum != 100)
            throw new RuntimeException("The percentage doesn't add up to 100");

        for(int i = 0 ; i < payees.size() ; i++) {
            double amountForUser = amt.get(i);
            User u = payees.get(i);
            payer.getAmountOwedBy().putIfAbsent(u , payer.getAmountOwedBy().getOrDefault(u , 0.0));
            u.getAmountOwedBy().putIfAbsent(payer, u.getAmountOwedBy().getOrDefault(payer, 0.0));

            payer.getAmountOwedBy().put(u, payer.getAmountOwedBy().get(u) + amountForUser);
            u.getAmountOwedBy().put(payer, u.getAmountOwedBy().get(payer) - amountForUser);
        }
    }
}
