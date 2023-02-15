public class DisplayManager {
    private static DisplayManager instance = null;
    private UserDAO userRepo;
    private DisplayManager(){userRepo = UserDAO.getInstance();}
    public static DisplayManager getInstance(){
        if(instance == null)
            instance = new DisplayManager();
        return instance;
    }

    void showAll(){
        userRepo.getAllUsers().stream().forEach(user -> showUser(user));
    }

    void print(User payer, User payee, double val){
        System.out.println(payer.getName() + " owes " + payee.getName() + " " + val);
    }

    void showUser(User user){
        if(user.getAmountOwedBy().isEmpty())
            System.out.println("No Balances");
        else
            user.getAmountOwedBy().entrySet().forEach(userDoubleEntry -> {
                double val = userDoubleEntry.getValue();
                User key = userDoubleEntry.getKey();

                if(val < 0)
                    print(user, key, (-1*val));
                else if(val > 0)
                    print(key, user, val);
            });
    }
}
