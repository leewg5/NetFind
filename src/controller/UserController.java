package controller;

import model.dao.UserDao;

public class UserController {
    // (*) 싱글톤
    private UserController(){}
    private static final UserController instance = new UserController();
    public static UserController getInstance() {
        return instance;
    }

    // (*) 로그인 넘버 loginUno
    // 0이면 로그인 안 된 상태, 1이면 관리자 로그인, 2부터는 일반회원 로그인 (uno와 같음)
    // Note, Product에서는 2보다 큰 숫자여야하고, Sample에서는 숫자 1 상태여야 합니다.
    public static int loginUno = 0;

    // (*) Dao 싱글톤 호출
    private UserDao userDao = UserDao.getInstance();

}
