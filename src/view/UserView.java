package view;

public class UserView {

    // (*) 싱글톤
    private UserView(){}
    private static final UserView instance = new UserView();
    public static UserView getInstance() {
        return instance;
    }

}
