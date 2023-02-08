import java.util.List;

public class EqualSplit implements SplitStrategy{
    public void split(User payer, List<User> payees, double amount,
                      List<String> args) {
        double amt = amount / payees.size();
        for(User u : payees) {
            payer.getAmountOwedBy().putIfAbsent(u , payer.getAmountOwedBy().getOrDefault(u , 0.0));
            u.getAmountOwedBy().putIfAbsent(payer, u.getAmountOwedBy().getOrDefault(payer, 0.0));

            payer.getAmountOwedBy().put(u, payer.getAmountOwedBy().get(u) + amt);
            u.getAmountOwedBy().put(payer, u.getAmountOwedBy().get(payer) - amt);
        }
    }
}
