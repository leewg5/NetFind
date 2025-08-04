package model.dao;

import model.dto.SampleDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    public boolean sampleAdd( String sname , String sspec , String smaker ,String sunit ){
        // 1. 유효성 검사( 패스 )
        // 2. 데이터문제 없으면 묶음(객체) 하나로 만들기
        // --> 주의할점 매개변수와 동일한 생성자가 존재하지 않으면 오류 발생한다.
        SampleDto sampleDto = new SampleDto( 0 , sname , sspec , smaker , sunit );
        // 3. 객체화된 dto를 dao에게 전달후 결과를 받는다.
        boolean result = sampleDao.sampleAdd( sampleDto );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

    // (2) 샘플 삭제기능 구현
    public boolean sampleDel( int sno ){
        // 1. 유효성검사  // 2. 객체화
        // 3. dao에게 삭제할 번호 전달후 결과를 받는다.
        boolean result = sampleDao.sampleDel( sno );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

    // (3) 샘플 조회기능 구현
    public ArrayList<SampleDto> samplePrint(){
        // - 유효성검사 ~ // - 매개변수~
        // 3. dao에게 요청후 결과받기
        ArrayList<SampleDto> result = sampleDao.samplePrint();
        // 4. 결과를 view에게 리턴한다.
        return result;
    }
    // (4) 샘플번호와 제품번호 비교
    public boolean sampleProductPrint( int sno  ) {
        // boolean  : 해당 메소드 실행 결과를 true(저장성공) false(저장실패) 반환하기 위한 타입

        try {
            // 1. SQL 작성한다.
            String sql = "select * from sample where sno=? ";
            // 2. SQL 기재한다.
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입 , 현재 ? 1개
            ps.setInt( 1, sno ); // SQL내 1번?에 매개변수로 받은 sno의 값 대입
            // 4. SQL 실행 , insert/update/delete 은 .executeUpdate();
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if ( rs.next() ) return true; // 1개이상 insert했으면 저장성공
            return false;   // 1개 이상 insert 못했으면 저장 실패
        }catch ( Exception e ) { System.out.println(e); } //  catch e
        return  false; // 예외 발생시 저장 실패
    }// func e



}// class e
