package view;

import controller.NoteController;
import controller.ProductController;
import controller.SampleController;
import controller.UserController;
import model.dto.ProductDto;
import model.dto.UserDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductView {
    // (*) 싱글톤
    private ProductView() {}
    private static final ProductView instance = new ProductView();
    public static ProductView getInstance() {
        return instance;
    }
    private Scanner scan = new Scanner(System.in);
    private NoteController noteController = NoteController.getInstance();
    private ProductController productController = ProductController.getInstance();
    private UserController userController = UserController.getInstance();


    // 3-1) 제품 등록
    public void productAdd() {
        SampleView.getInstance().samplePrint();
        try {
            // 1. 입력받기
            System.out.println("============= 제품 등록 페이지 =============");
            System.out.print("추가할 제품의 번호 > ");
            int sno = scan.nextInt();
            System.out.println("=========================================");
            System.out.print("가격 : ");
            int pprice = scan.nextInt();
            System.out.print("재고 : ");
            int pstock = scan.nextInt();
            System.out.print("상태 (신품/중고) : ");
            String pstatus = scan.next();

            // pstatus 체크
            // 2. pstatus를 체크 후 컨트롤러에 전달, 리턴값 저장
            boolean result;
            if (pstatus.equals("신품")) {
                result = productController.productAdd(sno, pprice, pstock, true);
            } else if (pstatus.equals("중고")) {
                result = productController.productAdd(sno, pprice, pstock, false);
            } else {
                System.out.println("[경고] 유효한 값을 입력해주세요.");
                return;
            }

            // 3. 리턴값 출력
            if (result) {
                System.out.println("[안내] 제품 등록 완료");
            } else {
                System.out.println("[경고] 제품 등록 실패");
            }
        } catch (InputMismatchException e) {
            System.out.println("[경고] 유효한 값을 입력해주세요.");
            scan = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("[경고] 오류가 발생하였습니다. 관리자에게 문의해주세요.");
        }
    } // func end

    // 3-2) 전체 제품 조회
    public void productPrint() {
        // 1. 입력받기 (없음)
        // 2. 컨트롤러 전달 후 리턴값 저장
        ArrayList<ProductDto> result = productController.productPrint();

        // 3. 화면 구현
        System.out.println("============= 전체제품조회 페이지 =============");
        System.out.println("번호 | 제품명 | 규격 | 제조사 | 단위 | 가격 | 재고 | 상태");
        for (ProductDto dto : result) {
            System.out.printf("%d. %s %s (%s, %s) %d , %d, %s\n",
                    dto.getPno(), dto.getSname(), dto.getSspec(), dto.getSmaker(), dto.getSunit(),
                    dto.getPprice(), dto.getPstock(), dto.isPstatus()?"신품":"중고");
        }
        System.out.println("----------------------------------------------\n");
    }

    // 3-3) 전체 제품 조회 (판매자 상세 페이지용)
    public void productDetailPrint() {
        SampleView.getInstance().samplePrint();
        try {
            // 1. 입력받기 (pno)
            System.out.print("상세 조회할 판매자의 번호를 입력하세요 > ");
            int uno = scan.nextInt();
            // 2. 컨트롤러 전달 후 리턴값 저장
            ArrayList<ProductDto> pResult = productController.productPrint(uno);
            ArrayList<UserDto> uResult = userController.userPrint(uno);

            if (pResult.isEmpty() || uResult.isEmpty()) {
                System.out.println("[경고] 존재하지 않는 판매자 번호입니다.");
                return;
            }
            // 3. 화면구현
            System.out.println("============= 판매자 상세 페이지 =============");
            System.out.println("번호 | 제품명 | 규격 | 제조사 | 단위 | 가격 | 재고 | 상태");
            for (ProductDto dto : pResult) {
                System.out.printf("%d. %s %s (%s, %s) %d , %d, %s\n",
                        dto.getPno(), dto.getSname(), dto.getSspec(), dto.getSmaker(), dto.getSunit(),
                        dto.getPprice(), dto.getPstock(), dto.isPstatus()?"신품":"중고");
            }
            System.out.println("----------------------------------------------\n");
            System.out.println("사업자명 | 사업자번호 | 사업장주소지");
            for (UserDto dto : uResult) {
                System.out.printf("%s | %s | %s\n", dto.getUbname(), dto.getUbnumber(), dto.getUblocation());
                System.out.printf("☎Tel | %s\n", dto.getUphone());
            }

            // 4. 쪽지 입력
            System.out.print("쪽지를 보내시겠습니까? [Y/N] ");
            String note = scan.next();
            note = note.toUpperCase(); // y/n을 Y/N으로 (소문자를 대문자로) 변경
            if (note.equals("Y")) {
                System.out.print("쪽지 내용 : ");
                scan.nextLine();
                String ncontext = scan.nextLine();

                boolean result = noteController.noteAdd(uno, ncontext);
                // 5. 쪽지 리턴값 출력
                if (result) {
                    System.out.println("[안내] 쪽지 전송 완료");
                } else {
                    System.out.println("[경고] 쪽지 전송 실패");
                }
            } else if (note.equals("N")) {
                return;
            } else {
                System.out.println("[경고] 유효한 값을 입력해주세요.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("[경고] 유효한 값을 입력해주세요.");
            scan = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("[경고] 오류가 발생하였습니다. 관리자에게 문의해주세요.");
        }


    } // func end

    // 3-4) 제품 수정
    public void productUpdate(){
        productPrint();
        try {
            // 1. 입력받기
            System.out.print("수정할 제품의 번호 > ");
            int pno = scan.nextInt();
            System.out.print("가격 : ");
            int pprice = scan.nextInt();
            System.out.print("재고 : ");
            int pstock = scan.nextInt();
            System.out.print("상태 (신품/중고): ");
            String pstatus = scan.next();

            // 2. pstatus를 체크 후 컨트롤러에 전달, 리턴값 저장
            boolean result;
            if (pstatus.equals("신품")) {
                result = productController.productUpdate(pno, pprice, pstock, true);
            } else if (pstatus.equals("중고")) {
                result = productController.productUpdate(pno, pprice, pstock, false);
            } else {
                System.out.println("[경고] 유효한 값을 입력해주세요.");
                return;
            }

            // 3. 리턴값 출력
            if (result) {
                System.out.println("[안내] 제품 수정 완료");
            } else {
                System.out.println("[경고] 제품 수정 실패");
            }
        } catch (InputMismatchException e) {
            System.out.println("[경고] 유효한 값을 입력해주세요.");
            scan = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("[경고] 오류가 발생하였습니다. 관리자에게 문의해주세요.");
        }
    }

    // 3-5) 제품 삭제
    public void productDelete(){
        productPrint();
        try {
            // 1. 입력받기
            System.out.print("삭제할 제품의 번호 > ");
            int pno = scan.nextInt();

            // 2. pno 를 컨트롤러에 전달, 리턴값 저장
            boolean result = productController.productDelete(pno);

            // 3. 리턴값 출력
            if (result) {
                System.out.println("[안내] 제품 삭제 완료");
            } else {
                System.out.println("[경고] 제품 삭제 실패");
            }
        } catch (InputMismatchException e) {
            System.out.println("[경고] 유효한 값을 입력해주세요.");
            scan = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("[경고] 오류가 발생하였습니다. 관리자에게 문의해주세요.");
        }
    }

    // 3-6) 장바구니 등록
    public void cartAdd(){
        try {
            // 1. controller에게 요청후 결과받기
            ArrayList<ProductDto> list = productController.productPrint();
            // 2. 결과에 따른 화면구현
            System.out.println(" 번호 \t 제품명 \t 규격 \t 제조사 \t 단위  \t\t 가격 \t 재고 \t 상태");
            for (ProductDto dto : list) { //향상된 for문, for( 항목타입 변수명 : 리스트명) { }
                String status = dto.isPstatus() ? "신품" : "중고";
                System.out.printf(" %s \t %s \t %s \t %s \t %s \t %s \t %s \t %s \n", dto.getPno() , dto.getSname() , dto.getSspec() , dto.getSmaker() , dto.getSunit() , dto.getPprice() , dto.getPstock() , status);
            }
            for (;;) {
                // 1. 입력받기
                System.out.print("장바구니에 담을 번호를 입력하세요 > ");
                int num = scan.nextInt();
                System.out.print("수량을 입력하세요 : ");
                int stock = scan.nextInt();

                // 2. 컨트롤러에 전달 및 리턴
                int result = productController.cartAdd(num, stock);
                if (result == 1) {
                    System.out.println("[경고] 담으려는 물품의 수량이 재고의 양보다 많습니다.");
                    System.out.println("[경고] 장바구니에 물품을 담지 못했습니다.");
                } else if (result == 2) {
                    System.out.println("[경고] 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    System.out.println("[경고] 장바구니에 물품을 담지 못했습니다.");
                }
                // 3. 장바구니 입력 완료 후 반복 여부 확인
                String check = "N";
                for (;;) {
                    System.out.print("계속 담으시겠습니까? [Y/N] ");
                    check = scan.next();
                    check = check.toUpperCase(); // y/n을 Y/N으로 (소문자를 대문자로) 변경
                    if (check.equals("Y")) break;
                    else if (check.equals("N")) return;
                    else {
                        System.out.println("[경고] 유효한 값을 입력해주세요.");
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("[경고] 유효한 값을 입력해주세요.");
            scan = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("[경고] 오류가 발생하였습니다. 관리자에게 문의해주세요.");
        }
    }

    // 3-7) 장바구니 조회
    public void cartPrint(){
        // 1. 입력받기 (없음)
        // 2. 컨트롤러 전달 후 리턴값 저장
        ArrayList<ProductDto> result = productController.cartPrint();

        // 3. 화면 구현
        System.out.println("번호 \t 제품정보 \t\t\t\t 가격 \t 수량");
        for (ProductDto dto : result) {
            System.out.printf("%d. %s %s (%s, %s) %d , %d\n",
                    dto.getPno(), dto.getSname(), dto.getSspec(), dto.getSmaker(), dto.getSunit(),
                    dto.getPprice(), dto.getPstock());
        }
        System.out.println("----------------------------------------------\n");
    }

    // 3-8) 장바구니 삭제
    public void cartDelete(){
        try {
            // 1. 입력받기
            System.out.print("장바구니 목록 전체를 삭제하시겠습니까? [Y/N] ");
            String check = scan.next();
            check = check.toUpperCase(); // y/n을 Y/N으로 (소문자를 대문자로) 변경
            if (check.equals("Y")) {
                // 2. 로직 처리
                boolean result = productController.cartDelete();
                // 3. 리턴값 출력
                if (result) {
                    System.out.println("[안내] 장바구니 전체 삭제되었습니다.");
                } else {
                    System.out.println("[경고] 장바구니 삭제에 실패하였습니다.");
                }
            } else if (check.equals("N")) {
                return;
            } else {
                System.out.println("[경고] 유효한 값을 입력해주세요.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("[경고] 유효한 값을 입력해주세요.");
            scan = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("[경고] 오류가 발생하였습니다. 관리자에게 문의해주세요.");
        }
    }
}
