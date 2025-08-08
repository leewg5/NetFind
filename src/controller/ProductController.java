package controller;

import model.dao.ProductDao;
import model.dto.ProductDto;

import java.util.ArrayList;

public class ProductController {
    // (*)싱글톤
    private ProductController() {}
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance() {
        return instance;
    }
    ProductDao dao = ProductDao.getInstance();

    // 1. 제품 등록
    public boolean productAdd(int sno, int pprice, int pstock, boolean pstatus) {
        ProductDto dto = new ProductDto(sno, pprice, pstock, pstatus);
        return dao.productAdd(dto);
    }

    // 2. 전체 제품 조회 (매개변수 : x)
    public ArrayList<ProductDto> productPrint() {
        return dao.productPrint();
    }

    // 3. 전체 제품 조회 (매개변수 : int uno)
    public ArrayList<ProductDto> productPrint(int uno) {
        return dao.productPrint(uno);
    }

    // 4. 제품 수정
    public boolean productUpdate(int pno, int pprice, int pstock, boolean pstatus) {
        ProductDto dto = new ProductDto(pprice, pstock, pstatus, pno);
        return dao.productUpdate(dto);
    }

    // 5. 제품 삭제
    public boolean productDelete(int pno) {
        return dao.productDelete(pno);
    }

    // 6. 장바구니 등록
    public int cartAdd(int num, int stock) {
        return dao.cartAdd(num, stock);
    }

    // 7. 장바구니 조회
    public ArrayList<ProductDto> cartPrint() {
        return dao.cartPrint();
    }

    // 8. 장바구니 삭제
    public boolean cartDelete() {
        return dao.cartDelete();
    }

    // 9. 검색 기능
    public ArrayList<ProductDto> productSearch(String sname) {
        return dao.productSearch(sname);
    }
}
