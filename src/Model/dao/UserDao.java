package Model.dao;

public class UserDao {
    // (*) 싱글톤
    private UserDao(){}
    private static final UserDao instance = new UserDao();
    public static UserDao getInstance() {
        return instance;
    }

}
