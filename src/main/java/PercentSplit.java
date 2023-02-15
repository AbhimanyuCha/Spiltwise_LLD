import java.util.ArrayList;
import java.util.List;

public class PercentSplit extends SplitStrategy{
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

        distribute(payer, payees, amt);
    }
}
