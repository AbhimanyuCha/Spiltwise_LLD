import java.util.List;

public abstract class SplitStrategy {
    public abstract void split(User payer, List<User> payees, double amount, List<String> args);
    public void distribute(User payer, List<User> payees, List<Double> amountList){
        for(int i = 0 ; i < payees.size() ; i++) {
            double amountForUser = amountList.get(i);
            User u = payees.get(i);
            payer.getAmountOwedBy().putIfAbsent(u , payer.getAmountOwedBy().getOrDefault(u , 0.0));
            u.getAmountOwedBy().putIfAbsent(payer, u.getAmountOwedBy().getOrDefault(payer, 0.0));

            payer.getAmountOwedBy().put(u, payer.getAmountOwedBy().get(u) + amountForUser);
            u.getAmountOwedBy().put(payer, u.getAmountOwedBy().get(payer) - amountForUser);
        }
    }
}
