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

    void showUser(User user){
        if(user.getAmountOwedBy().isEmpty())
            System.out.println("No Balances");
        else
            user.getAmountOwedBy().entrySet().forEach(userDoubleEntry -> {
                double val = userDoubleEntry.getValue();
                User key = userDoubleEntry.getKey();
                if(val < 0)
                    System.out.println(user.getName() + " owes " + key.getName() + " " + (-1*val));
                else if(val > 0)
                    System.out.println(key.getName() + " owes " + user.getName() + " " + val);
            });
    }
}
