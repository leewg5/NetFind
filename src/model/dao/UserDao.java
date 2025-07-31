package model.dao;

import model.dto.UserDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    // (*) 싱글톤
    private UserDao(){ connect(); } // 연동 함수 생성자에 넣기
    private static final UserDao instance = new UserDao();
    public static UserDao getInstance() {
        return instance;
    }

    // (*) DB 연동
    private String db_url = "jdbc:mysql://localhost:3306/netFind";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;

    // (*) 드라이버매니저 연동 함수
    private void connect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url , db_user , db_password);
        } catch (Exception e){
            System.out.println(e);
        } // catch end
    } // func end

    // (1) 사용자등록 (회원가입)
    // 아이디, 비밀번호, 연락처, 사용자명, 사업자명, 사업자번호, 사업장주소를 입력 받아 DB에 저장한다.
    // String uid
    //String upwd
    //String uphone
    //String uname
    //String ubname
    //String ubnumber
    //String ublocation
    // 반환 불리언
    public boolean userAdd(UserDto userDto){
        try {
            // 1. SQL 작성
            String sql = "insert into user(uid, upwd, uphone, uname, ubname, ubnumber, ublocation) values (?, ?, ?, ?, ?, ?, ?);";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQl 매개변수 대입
            ps.setString(1, userDto.getUid());
            ps.setString(2, userDto.getUpwd());
            ps.setString(3, userDto.getUphone());
            ps.setString(4, userDto.getUname());
            ps.setString(5, userDto.getUbname());
            ps.setString(6, userDto.getUbnumber());
            ps.setString(7, userDto.getUblocation());
            // 4. SQL 실행 : excuteUpdate()
            int count = ps.executeUpdate();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if (count >= 1) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
        } // catch end
        return false;
    } // func end

    // (2) 사용자정보수정
    // 1. checkPwd()로 사용자에게 비밀번호를 입력받아 일치하는지 유효성 검사한다. (컨트롤러에서 기능 섞기)
    // 2. 새로운 비밀번호, 연락처, 사용자명을 입력 받아 DB에 업데이트한다.
    // String upwd
    // String uphone
    // String uname
    // 반환 불리언
    public boolean userUpdate(UserDto userDto){ // dto의 생성자로 만든 멤버변수를 매개변수로 넣기
        try {
            // 1. SQL 작성
            String sql = "update user set upwd = ? , uphone = ? , uname = ?;";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입, SQL 문법내 ? 개수만큼
            ps.setString(1, userDto.getUpwd());
            ps.setString(2, userDto.getUphone());
            ps.setString(3, userDto.getUname());
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 로직/리턴/확인
            if (count == 1){
                return true; // 수정 성공
            } else {
                return false; // 1 아니면 수정 실패
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
            return false; // catch에서 막히면 수정 실패
    } // func end

    // (3) 사용자탈퇴
    // 1. checkPwd()로 사용자에게 비밀번호를 입력받아 일치하는지 유효성 검사한다.
    // 2. 삭제 여부를 확인 후, 동의 시 DB의 해당 레코드를 영구 삭제한다.
    // 반환 없음
    public void userDelete(){
        // 1. SQL 작성
        // 2. SQL 기재
        // 3. SQL 매개변수 대입
        // 4. SQL 실행
        // 5. SQL 결과 확인(반환 없음)

    } // func end

    // (4) 상세사용자조회
    // 1. 사용자(구매자)에게 제품번호를 입력받는다.
    // 2. 제품번호를 PK로 하고, 회원번호를 FK로 조회하여 일치하는 사용자DB(판매자)를 찾는다.
    // 3. (제품번호, 제품명, 규격, 제조사, 단위, 가격, 재고, 상태를 DB에서 호출한다.)재영님구현
    // 그리고 사업자명, 사업자번호, 사업장주소지, 연락처를 DB에서 호출한다.
    // 4.  사용자DB(판매자)의 uno를 받는 사람(nsend)의 FK로 받아, 쪽지 기능을 실행한다.
    // int pno
    // 반환 UserDto
    public UserDto userPrint(){
        UserDto dto = new UserDto();
        // 1. SQL 작성
        // 2. SQL 기재
        // 3. SQL 매개변수 대입
        // 4. SQL 실행 select라서 executeQuery()
        // 5. SQL 결과에 따른 로직/리턴/확인
        
        return dto;
    }

    // (5) 로그인
    // 1. 아이디와 비밀번호가 일치하면 로그인 세션을 부여한다.
    // 2. 메인 View 페이지로 이동한다.
    // * uid가 admin과 일치할 경우 loginUno 1, 그 외에는 loginUno 2를 부여한다.
    // String uid
    // String upwd
    // 반환 int(uno와 일치하는 int)
    public int login(){
        // 1. SQL 작성
        // 2. SQL 기재
        // 3. SQL 매개변수 대입
        // 4. SQL 실행 select라서 executeQuery()
        // 5. SQL 결과에 따른 로직/리턴/확인
        return 0;
    }

    // (6) 로그아웃
    // 로그인 세션 만료 처리하고 콘솔 종료한다.
    // (static 변수 활용)
    // 반환 X
    public void logout(){
        // 1. SQL 작성
        // 2. SQL 기재
        // 3. SQL 매개변수 대입
        // 4. SQL 실행 select라서 executeQuery()
        // 5. SQL 결과에 따른 확인 (매개변수X)
    }

    // (*) 비밀번호유효성검사
    // 현재 사용자의 upwd를 입력 받아, 일치하는지 확인하는 유효성 검사 메소드
    // String upwd
    // 반환 불리언
    public boolean checkPwd(String upwd){
        try {
            // 1. SQL 작성
            String sql = "select * from user where upwd = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입
            ps.setString(1 , upwd);
            // 4. SQL 실행 select라서 executeQuery()
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if (rs.next()){ //
                return true;
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false;
    } // func end



} // class end
