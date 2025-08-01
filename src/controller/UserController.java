package controller;

import model.dao.UserDao;
import model.dto.UserDto;

public class UserController {
    // (*) 싱글톤
    private UserController() {
    }

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

    // (1) 회원가입 컨트롤
    //    String uid
    //    String upwd
    //    String uphone
    //    String uname
    //    String ubname
    //    String ubnumber
    //    String ublocation
    //    반환 불리언
    public boolean userAdd(String uid, String upwd, String uphone, String uname, String ubname, String ubnumber, String ublocation) {
        // 1. 유효성 검사 (공란체크)
        if (uid == null || uid.trim().isEmpty()) {
            return false;
        } else if (upwd == null || uid.trim().isEmpty()) {
            return false;
        } else if (uphone == null || uid.trim().isEmpty()) {
            return false;
        } else if (uname == null || uid.trim().isEmpty()) {
            return false;
        } else if (ubname == null || uid.trim().isEmpty()) {
            return false;
        } else if (ubnumber == null || uid.trim().isEmpty()) {
            return false;
        } else if (ublocation == null || uid.trim().isEmpty()) {
            return false;
        }
        // 2. 객체화
        UserDto userDto = new UserDto(0, uid, upwd, uphone, uname, ubname, ubnumber, ublocation);
        // 3. 객체화된 dto dao에게 전달 후 결과 반환
        boolean result = userDao.userAdd(userDto);
        // 4. view에게 리턴
        return result;
    } // func end

    // (2) 사용자정보수정
    //    1. checkPwd()로 사용자에게 비밀번호를 입력받아 일치하는지 유효성 검사한다. (컨트롤러에서 기능 섞기)
    //    2. 새로운 비밀번호, 연락처, 사용자명을 입력 받아 DB에 업데이트한다.
    //    String upwd
    //    String uphone
    //    String uname
    //    반환 불리언
    public boolean userUpdate( ){


        return false;
    }

    // (5) 로그인 컨트롤
    public int login(String uid, String upwd) {
        // 1. 유효성 검사 (공란체크, uid가 admin일 때)
        return 0;
    } // func end


    // (*) 비밀번호 체크 메소드 활용 시
    // uno와 loginUno가 일치하는지 확인
    

} // class end


        

