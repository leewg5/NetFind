package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SampleDao {
    // (*)싱글톤
    private SampleDao(){ connect(); }
    private static final SampleDao instance = new SampleDao();
    public static SampleDao getInstance(){
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
            // System.out.println( "연동성공");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // (1) 샘플 등록기능구현

    // (2) 샘플 삭제기능 구현

    // (3) 샘플 조회기능 구현

    // (4) 샘플번호와 제품번호 비교


}// class e
