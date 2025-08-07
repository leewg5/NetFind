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
    public boolean sampleAdd( SampleDto sampleDto ){
        // boolean  : 해당 메소드 실행 결과를 true(저장성공) false(저장실패) 반환하기 위한 타입
        // SampleDto sampleDto    : controller로부터 저장할 값들을 dto로 구성해서 받는 매개변수
        try {
            // 1. SQL 작성한다.
            String sql = "insert into sample( sname , sspec , smaker , sunit ) values( ? , ? , ? , ? )";
            // 2. SQL 기재한다.
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입 , 현재 ? 4개
            ps.setString(1, sampleDto.getSname() );
            ps.setString(2, sampleDto.getSspec() );
            ps.setString( 3, sampleDto.getSmaker() );
            ps.setString( 4 , sampleDto.getSunit() );
            // 4. SQL 실행 , insert/update/delete 은 .executeUpdate();
            int count = ps.executeUpdate();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if (count >= 1) return true; // 1개이상 insert했으면 저장성공
            return false; // 1개 이상 insert 못했으면 저장 실패
        } catch (Exception e) { System.out.println(e); }
        return false;  // 예외 발생시 저장 실패
    }

    // (2) 샘플 삭제기능 구현
    public boolean sampleDel( int sno ){
        try{
            // 1. SQL 작성한다.
            String sql = "delete from sample where sno = ?";
            // 2. SQL 기재한다.
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입
            ps.setInt( 1 , sno ); // SQL 문법내 첫번째 ? 의 INT타입으로 sno 값 대입
            // 4. SQL 실행 , insert/update/delete 문법 실행 결과는 처리된 레코드수 반환
            int count = ps.executeUpdate();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if( count == 1 ) { return true; } // <--- sql결과 1이면 취소성공
            else{ return false; } // <--- sql결과 1아니면 취소실패
        } catch (Exception e) { System.out.println(e); }
        return false; // <--- 예외발생시 삭제실패
    }

    // (3) 샘플 조회기능 구현
    public ArrayList<SampleDto> samplePrint(){
        ArrayList<SampleDto> list = new ArrayList<>(); // 조회된 레코드(DTO) 들을 저장할 리스트 선언
        try{
            // 1. SQL 작성한다.
            String sql = "select * from sample";
            // 2. SQL 기재한다.
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입 , SQL 문법에? (매개변수) 가 없어서 생략
            // 4. SQL 실행 , select = executeQuery()
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과에 따른 로직/리턴/확인
            // 1) select 조회결과를 레코드/행/가로단위 하나씩 조회
            while ( rs.next() ){ //rs.next() : ResultSet 인터페이스가 갖는 (조회)결과 테이블에서 다음 레코드 이동 뜻
                int sno = rs.getInt( "sno" ); // rs.get타입("가져올속성명 or 번호")
                String sname = rs.getString( "sname" );
                String sspec = rs.getString( "sspec" );
                String smaker = rs.getString( "smaker" );
                String sunit = rs.getString( "sunit" );
                SampleDto sampleDto = new SampleDto( sno , sname , sspec , smaker , sunit );
                // 3) 생성된 dto를 리스트에 담아주기
                list.add( sampleDto );
            } //while e
        } catch (Exception e) { System.out.println(e); }
        return list;
    }

    // (4) 샘플번호와 제품번호 비교
    public boolean sampleProductPrint( int sno ) {
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
