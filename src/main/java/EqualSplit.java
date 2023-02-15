import java.util.ArrayList;
import java.util.List;

public class EqualSplit extends SplitStrategy{
    public void split(User payer, List<User> payees, double amount,
                      List<String> args) {
        double amt = amount / payees.size();
        List<Double> amountList = new ArrayList<>();
        for(int i = 0 ; i < payees.size() ; i++) amountList.add(amt);
        distribute(payer, payees, amountList);
    }
}
