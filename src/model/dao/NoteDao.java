package model.dao;

import model.dto.NoteDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static controller.UserController.loginUno;

public class NoteDao {

    // * 싱글톤
    private NoteDao(){  connect(); }
    private static final NoteDao instance = new NoteDao();
    public static NoteDao getInstance() {
        return instance;
    }




    // (*) DB 연동
    private String db_url = "jdbc:mysql://localhost:3306/netFind";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //(1) 등록 기능 구현
    public boolean noteAdd(NoteDto noteDto){
        //boolean : 해당 메소드 실행 결과를 true(저장성공)false(저장실패) 반환 하기 위한 타입
        // BoardDto boardDto    : controller 로부터 저장할 값들을 dto로 구성해서 받는 매개변수
        try {
            String sql = "INSERT INTO note (nsend, nreceive, ncontext) VALUES (?, ?, ?);"; // 1. SQL 작성한다.
            PreparedStatement ps = conn.prepareStatement(sql);  // 2. SQL 기재한다..
            // 3. SQL 매개변수 대입 , 현재 ? 2개
            ps.setInt(1, loginUno); // nsend값
            ps.setInt(2, noteDto.getNreceive());
            ps.setString(3, noteDto.getNcontext());
            // 4. SQL 실행 , insert/update/delete 은 .executeUpdate();
            int count = ps.executeUpdate();
            // 5. sql 결과에 따른 로직/리턴/확인
            if (count >= 1) return true; // 1개 이상 insert 했으면 저장성공
            return false; // 1개이상 insert 못했으면 저장실패
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false; // 예외 발생시 저장실패.
    } // func end

    //(2) 전체조회 기능 구현
    public ArrayList<NoteDto> notePrint() {
        // public : 다른패키지의 Controller 가 접근하기 위해 public 다른패키지 접근 vs private 현재클래스만
        // ArrayList<BoardDto> : *배열* 대신에 다양한 편의성 기능을 제공하는 ArrayList 클래스
        ArrayList<NoteDto> list = new ArrayList<>();
        try{
            String sql = "select * from note"; //1. SQL 작성
            PreparedStatement ps = conn.prepareStatement(sql); // 2. SQL 기재
            //3. SQL 매개변수 대입, SQL 문법에 ?(매개변수)가 없어서 생략
            ResultSet rs = ps.executeQuery(); // 4. SQL 실행 , select = executeQuery()
            //5. SQL 결과에 따른 로직/리턴/확인
            //1) select 조회 결과를 레코드/행/가로단위 하나씩 조회
            while (rs.next()) { // rs.next() : ResultSet 인터페이스가 갖는 (조회)결과테이블에서 다음레코드 이동 뜻
                // 2) 현재 조회중인 레코드의 속성값 호출해서 dto 만들기
                int nno = rs.getInt("nno"); // rs.get타입("가져올속성명or번호")
                int nsend = rs.getInt("nsend");
                int nreceive = rs.getInt("nreceive");
                String ncontext = rs.getString("ncontext");
                String ndate = rs.getString("ndate");
                NoteDto noteDto = new NoteDto(nno, nsend, nreceive, ncontext, ndate);  // 레코드1개(열3개) --> DTO(멤버변수3개) 1개
                // 3) 생성된 dto를 리스트에 담아주기
                list.add(noteDto);
                } // while 반복문 종료
            }catch (Exception e){
                System.out.println(e);
            }
            return list;
        }//func end
}





