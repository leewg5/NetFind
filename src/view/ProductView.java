package view;

import model.dto.ProductDto;
import model.dto.UserDto;

import java.util.ArrayList;

//public class ProductView {
//
//    // 3-1) 제품 등록
//    public void productAdd(){
//        //samplePrint(); // 제품샘플조회 함수 호출
//        // 1. 입력받기
//        System.out.println("============= 제품등록 페이지 =============");
//        System.out.println("┌──────────────────┐");
//        System.out.println("│  추가할 제품 번호 > ");
//        System.out.println("└──────────────────┘");
//        int sno = scan.nextInt();
//        System.out.println("=========================================");
//        System.out.print("가격 : ");
//        int pprice = scan.nextInt();
//        System.out.print("재고 : ");
//        int pstock = scan.nextInt();
//        System.out.print("상태 : *신품이면 true, 중고이면 false 입력해주세요.*");
//        boolean pstatus = scan.nextBoolean();
//        // 2. 컨트롤러에 전달 후 리턴값 저장
//        boolean result = productController.productAdd(sno, pprice, pstock, pstatus);
//        // 3. 리턴값 출력
//        if(result){
//            System.out.println("[안내] 제품 등록 성공");
//        } else {
//            System.out.println("[경고] 제품 등록 실패");
//        }
//    } // func end
//
//    // 3-2) 전체 제품 조회
//    public void productPrint(){
//        // 1. 입력받기 (없음)
//        // 2. 컨트롤러 전달 후 리턴값 저장
//        ArrayList<ProductDto> result = productController.productPrint();
//
//        // 3. 화면 구현
//        System.out.println("============= 전체제품조회 페이지 =============");
//        System.out.printf("번호 \t 판매자 \t 제품정보 \t\t\t\t\t\t 가격");
//        for (ProductDto dto : result){
//            System.out.printf("%s \t %s \t %s \n", dto.getPno());
//        }
//
//    }
//
//    // 3-3) 전체 제품 조회 (판매자 상세 페이지용)
//    public void productPrint(int pno) {
//        // 1. 입력받기 (pno)
//
//        // 2. 컨트롤러 전달 후 리턴값 저장
//        ArrayList<ProductDto> result = productController.productPrint();
//        ArrayList<UserDto> result2 = userController.userPrint(pno);
//        // 3. 화면구현
//        System.out.println("============= 판매자 상세 제품 조회 페이지 =============");
//
//        userPrint(); // 사용자 조회 함수 호출
//
//    } // func end
//
//    // 3-4) 제품 수정
//    public void productUpdate(){
//
//    }
//
//    // 3-5) 제품 삭제
//    public void productDelete(){
//
//    }
//
//    // 3-6) 장바구니 등록
//    public void cartAdd(){
//
//    }
//
//    // 3-7) 장바구니 조회
//    public void cartPrint(){
//
//    }
//
//    // 3-8) 장바구니 삭제
//    public void cartDelete(){
//
//    }
//}
