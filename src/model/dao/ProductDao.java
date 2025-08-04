package model.dao;

import model.dto.ProductDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static controller.UserController.loginUno;

public class ProductDao {
    // (*) 싱글톤
    private ProductDao() { connect(); }
    private static final ProductDao instance = new ProductDao();
    public static ProductDao getInstance() {
        return instance;
    }
    private String db_url = "jdbc:mysql://localhost:3306/netFind";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;
    private ArrayList<ProductDto> list = new ArrayList<>(); // 제품 목록 역할을 수행하는 ArrayList
    private ArrayList<ProductDto> cart = new ArrayList<>(); // 장바구니 역할을 수행하는 ArrayList

    // (*) 데이터베이스 연동
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 1. 제품 등록
    public boolean productAdd(ProductDto dto) {
        try {
            // 1. 제품 등록 sql문
            String sql = "insert into product(sno, uno, pprice, pstock, pstatus) values (?, ?, ?, ?, ?)";
            // 2. Statement 준비
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. sql 문에 필요한 데이터 삽입
            ps.setInt(1, dto.getSno());
            ps.setInt(2, loginUno); // 로그인한 사용자의 static uno 삽입
            ps.setInt(3, dto.getPprice());
            ps.setInt(4, dto.getPstock());
            ps.setBoolean(5, dto.isPstatus());
            // 4. sql문 실행
            ps.executeUpdate();
            // 5. 실행 후 반환
            return true; // 실행 완료 시 true 반환
        } catch (Exception e) {
            System.out.println(e);
            return false; // 오류 발생 시 false 반환
        }
    }

    // 2. 전체 제품 조회 (매개변수 : x)
    public ArrayList<ProductDto> productPrint() {
        try {
            // 1. 제품 조회 sql문
            String sql = "select * from product join sample on product.sno = sample.sno";
            // 2. Statement 준비
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. sql문 실행
            ResultSet rs = ps.executeQuery();
            // 4. 레코드를 하나씩 객체로 만들어 list에 삽입
            while (rs.next()) {
                int pno = rs.getInt("pno");
                String sname = rs.getString("sname");
                String sspec = rs.getString("sspec");
                String smaker = rs.getString("smaker");
                String sunit = rs.getString("sunit");
                int pprice = rs.getInt("pprice");
                int pstock = rs.getInt("pstock");
                ProductDto record = new ProductDto(pno, sname, sspec, smaker, sunit, pprice, pstock, false);
                list.add(record);
            }
            // 5. 실행 후 반환
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // 3. 전체 제품 조회 (매개변수 : int uno)
    public ArrayList<ProductDto> productPrint(int uno) {
        try {
            // 1. 제품 조회 sql문
            String sql = "select * from product join sample on product.sno = sample.sno where uno = ?";
            // 2. Statement 준비
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. sql 문에 필요한 데이터 삽입
            ps.setInt(1, uno);
            // 4. sql문 실행
            ResultSet rs = ps.executeQuery();
            // 5. 레코드를 하나씩 객체로 만들어 list에 삽입
            while (rs.next()) {
                int pno = rs.getInt("pno");
                String sname = rs.getString("sname");
                String sspec = rs.getString("sspec");
                String smaker = rs.getString("smaker");
                String sunit = rs.getString("sunit");
                int pprice = rs.getInt("pprice");
                int pstock = rs.getInt("pstock");
                boolean pstatus = rs.getBoolean("pstatus");
                ProductDto record = new ProductDto(pno, sname, sspec, smaker, sunit, pprice, pstock, pstatus);
                list.add(record);
            }
            // 6. 실행 후 반환
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // 4. 제품 수정
    public boolean productUpdate(ProductDto dto) {
        try {
            // 1. 제품 수정 sql문
            String sql = "update product set pprice = ?, pstock = ?, pstatus = ? where pno = ?";
            // 2. Statement 준비
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. sql 문에 필요한 데이터 삽입
            ps.setInt(1, dto.getPprice());
            ps.setInt(2, dto.getPstock());
            ps.setBoolean(3, dto.isPstatus());
            ps.setInt(4, dto.getPno());
            // 4. sql문 실행
            ps.executeUpdate();
            // 5. 실행 후 반환
            return true; // 실행 완료 시 true 반환
        } catch (Exception e) {
            System.out.println(e);
            return false; // 오류 발생 시 false 반환
        }
    }

    // 5. 제품 삭제
    public boolean productDelete(int pno) {
        try {
            // 1. 제품 삭제 sql문
            String sql = "delete from product where pno = ?";
            // 2. Statement 준비
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. sql 문에 필요한 데이터 삽입
            ps.setInt(1, pno);
            // 4. sql문 실행
            ps.executeUpdate();
            // 5. 실행 후 반환
            return true; // 실행 완료 시 true 반환
        } catch (Exception e) {
            System.out.println(e);
            return false; // 오류 발생 시 false 반환
        }
    }

    // 6. 장바구니 등록
    public int cartAdd(int num, int stock) {
        try {
            // 1. 제품 조회 sql문
            String sql = "select * from product join sample on product.sno = sample.sno where pno = ?";
            // 2. Statement 준비
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. sql 문에 필요한 데이터 삽입
            ps.setInt(1, num);
            // 4. sql문 실행
            ResultSet rs = ps.executeQuery();
            // 5. 찾은 레코드를 cart에 삽입
            rs.next();
            int pno = rs.getInt("pno");
            String sname = rs.getString("sname");
            String sspec = rs.getString("sspec");
            String smaker = rs.getString("smaker");
            String sunit = rs.getString("sunit");
            int pprice = rs.getInt("pprice");
            int pstock = rs.getInt("pstock");
            // 6. 재고 파악 (입력받은 값이 재고보다 많을 시 재고 부족으로 return)
            if (pstock < stock) return 1; // 재고보다 담으려는 물품이 많으면 담을 수 없어야 함 (재고 부족으로 1 반환)
            else pstock = stock; // 재고보다 담으려는 물품이 적으면 입력받은 값을 담음
            ProductDto record = new ProductDto(pno, sname, sspec, smaker, sunit, pprice, pstock, false);
            cart.add(record);
            // 7. 실행 후 반환
            return 0; // 실행 완료 시 0 반환
        } catch (Exception e) {
            System.out.println(e);
            return 2; // 오류 발생 시 2 반환
        }
    }

    // 7. 장바구니 조회
    public ArrayList<ProductDto> cartPrint() {
        return cart;
    }

    // 8. 장바구니 삭제
    public boolean cartDelete() {
        cart = new ArrayList<>(); // cart ArrayList를 초기화함
        return true;
    }
}
