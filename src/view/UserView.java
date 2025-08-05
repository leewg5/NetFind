package view;

import controller.NoteController;
import controller.ProductController;
// import controller.SampleController;
import controller.UserController;
import model.dto.ProductDto;
import model.dto.UserDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static controller.UserController.loginUno;

public class UserView {
    // (*) 싱글톤
    private UserView(){}
    private static final UserView instance = new UserView();
    public static UserView getInstance() {
        return instance;
    }

    // (*) 컨트롤러 싱글톤 호출(
    private UserController userController = UserController.getInstance();
//    private SampleController sampleController = SampleController.getInstance();
    private ProductController productController = ProductController.getInstance();
    private NoteController noteController = NoteController.getInstance();

    // (*) 입력 객체 만들기
    Scanner scan = new Scanner(System.in);

    // (*) 로그인 화면 구현
    public void index(){
        for ( ; ;) {
            try {
                System.out.println(
                        "╔══════════════════════════════════════════════════════════════════════════════╗\n" +
                                "║                                                                              ║\n" +
                                "║       ███╗   ██╗ ███████╗ ████████╗ ███████╗ ██╗ ███╗   ██╗ ██████╗          ║\n" +
                                "║       ████╗  ██║ ██╔════╝ ╚══██╔══╝ ██╔════╝ ██║ ████╗  ██║ ██╔══██╗         ║\n" +
                                "║       ██╔██╗ ██║ █████╗      ██║    █████╗   ██║ ██╔██╗ ██║ ██║  ██║         ║\n" +
                                "║       ██║╚██╗██║ ██╔══╝      ██║    ██╔══╝   ██║ ██║╚██╗██║ ██║  ██║         ║\n" +
                                "║       ██║ ╚████║ ███████╗    ██║    ██║      ██║ ██║ ╚████║ ██████╔╝         ║\n" +
                                "║       ╚═╝  ╚═══╝ ╚══════╝    ╚═╝    ╚═╝      ╚═╝ ╚═╝  ╚═══╝ ╚═════╝          ║\n" +
                                "║                                                                              ║\n" +
                                "╚══════════════════════════════════════════════════════════════════════════════╝");
                System.out.println("====================== Net Find에 오신 것을 환영합니다 ======================");
                System.out.println("┌───────────┐ ┌────────────┐");
                System.out.println("  1. 로그인      2. 회원가입  ");
                System.out.println("└───────────┘ └────────────┘");
                System.out.println("========================================================================");
                System.out.println("  선택 >    ");
                int select = scan.nextInt();
                if (select == 1) {
                    login();
                } else if (select == 2) {
                    userAdd();
                }
            } catch (InputMismatchException e){
                System.out.println("[경고] 정수를 입력해주세요. <다시 입력>");
                scan = new Scanner(System.in); // 입력객체 초기화로 오류 데이터 지우기
            } catch (Exception e){
                System.out.println("[오류] 관리자에게 문의해주세요. 010-1234-5678");
            } // catch end
        } // for end
    } // func end

    // (*) 관리자 메인 화면 구현
    public void adminMain(){
        for( ; ;) {
            try {
                System.out.println("=========================== Net Find 메인 페이지 ===========================");
                System.out.println("┌─────────────┐ ┌─────────────┐ ┌────────────┐ ┌────────────┐ ┌───────────┐");
                System.out.println("  0. 샘플관리      1. 판매등록      2. 조회구매      3. 마이페이지   4. 로그아웃 ");
                System.out.println("└─────────────┘ └─────────────┘ └────────────┘ └────────────┘ └───────────┘");
                System.out.println("===========================================================================");
                System.out.println("  선택 >     ");
                int select = scan.nextInt();
                if (select == 0) {
                    sampleIndex();
                } else if (select == 1) {
                    productIndex();
                } else if (select == 2) {
                    cartIndex();
                } else if (select == 3) {
                    mypageIndex();
                } else if (select == 4) {
                    logout();
                } else {
                    System.out.println("[경고] 제시한 번호를 입력해주세요.");
                }
            }catch (InputMismatchException e){
                System.out.println("[경고] 정수를 입력해주세요. <다시 입력>");
                scan = new Scanner(System.in);
            } catch (Exception e){
                System.out.println("[오류] 관리자에게 문의해주세요. 010-1234-5678");
            } // catch end
        } // for end
    } // admin index end


    // (*) 일반사용자 메인 화면 구현
    public void main(){
        for( ;;) {
            try {
                System.out.println("=========================== Net Find 메인 페이지 ===========================");
                System.out.println("┌─────────────┐ ┌────────────┐ ┌─────────────┐ ┌────────────┐");
                System.out.println("  1. 판매등록      2. 조회구매      3. 마이페이지    4. 로그아웃 ");
                System.out.println("└─────────────┘ └────────────┘ └─────────────┘ └────────────┘");
                System.out.println("========================================================================");
                System.out.println("  선택 >    ");
                int select = scan.nextInt();
                if (select == 1) {
                    productIndex();
                } else if (select == 2) {
                    cartIndex();
                } else if (select == 3) {
                    mypageIndex();
                } else if (select == 4) {
                    logout();
                } else {
                    System.out.println("[경고] 제시한 번호를 입력해주세요.");
                }
            }catch (InputMismatchException e){
                System.out.println("[경고] 정수를 입력해주세요. <다시 입력>");
                scan = new Scanner(System.in);
            } catch (Exception e){
                System.out.println("[오류] 관리자에게 문의해주세요. 010-1234-5678");
            } // catch end
        } // for end
    } // main index end

    // *) 제품 샘플 등록 인덱스
    public void sampleIndex(){
        try {
            for (; ; ) {
                System.out.println("========================== 제품 샘플 등록 페이지 ==========================");
                System.out.println("┌────────────────┐ ┌────────────────┐ ┌────────────────┐ ┌────────────┐");
                System.out.println("  1. 제품샘플등록      2. 제품샘플삭제      3. 제품샘플조회      4. 홈 화면  ");
                System.out.println("└────────────────┘ └────────────────┘ └────────────────┘ └────────────┘");
                System.out.println("========================================================================");
                int selectSample = scan.nextInt();
                if (selectSample == 1) {
                    sampleAdd();
                } else if (selectSample == 2) {
                    sampleDel();
                } else if (selectSample == 3) {
                    samplePrint();
                } else if (selectSample == 4) {
                    if(loginUno == 1){
                        adminMain();
                    } else {
                        main();
                    }
                } else {
                    System.out.println("[경고] 제시한 번호를 입력해주세요.");
                }
            } // for end
        } catch (Exception e){
            System.out.println(e);
        }
    } // func end

    // *) 판매 등록 인덱스
    public void productIndex(){
        try {
            for (; ; ) {
                System.out.println("============================ 판매 등록 페이지 ============================");
                System.out.println("┌────────────────┐ ┌────────────────┐ ┌────────────────┐ ┌────────────┐");
                System.out.println("   1. 제품 등록         2. 제품 수정        3. 제품삭제         4. 홈 화면  ");
                System.out.println("└────────────────┘ └────────────────┘ └────────────────┘ └────────────┘");
                System.out.println("========================================================================");
                int selectProduct = scan.nextInt();
                if (selectProduct == 1) {
                    productAdd();
                } else if (selectProduct == 2) {
                    productUpdate();
                } else if (selectProduct == 3) {
                    productDelete();
                } else if (selectProduct == 4) {
                    if (loginUno == 1){
                        adminMain();
                    } else {
                        main();
                    }
                } else {
                    System.out.println("[경고] 제시한 번호를 입력해주세요.");
                }
            } // for end
        } catch (Exception e){
            System.out.println(e);
        } // catch end
    } // func end

    // *) 장바구니 인덱스
    public void cartIndex(){
        try{
            for(;;){
                System.out.println("============================ 조회 구매 페이지 ============================");
                System.out.println("┌────────────────┐ ┌────────────────┐ ┌────────────────┐ ┌───────────────────┐  ┌───────────┐");
                System.out.println("  1. 장바구니담기       2. 장바구니확인     3. 장바구니삭제      4. 판매자상세페이지      5. 홈 화면 ");
                System.out.println("└────────────────┘ └────────────────┘ └────────────────┘ └───────────────────┘  └───────────┘");
                System.out.println("========================================================================");
                int selectCart = scan.nextInt();
                if (selectCart == 1) {
                    cartAdd();
                } else if (selectCart == 2) {
                    cartPrint();
                } else if (selectCart == 3) {
                    cartDelete();
                } else if (selectCart == 4) {
                    productPrint();
                    userPrint();
                    // 쪽지전송기능 판매자 선택 빼고 적용
                } else if (selectCart == 5) {
                    if (loginUno == 1){
                        adminMain();
                    } else {
                        main();
                    }
                } else {
                    System.out.println("[경고] 제시한 번호를 입력해주세요.");
                }
            } // for end
        } catch (Exception e){
            System.out.println(e);
        }
    }

    // *) 마이페이지 인덱스
    public void mypageIndex(){
        try{
            for (;;){
                System.out.println("============================ 마이 페이지 ============================");
                System.out.println("┌────────────────┐ ┌────────────────┐ ┌─────────────┐ ┌────────────┐");
                System.out.println("  1. 사용자정보수정     2. 사용자정보삭제     3. 쪽지기능      4. 홈 화면    ");
                System.out.println("└────────────────┘ └────────────────┘ └─────────────┘ └────────────┘");
                System.out.println("=====================================================================");
                int selectUser = scan.nextInt();
                if (selectUser == 1) {
                    userUpdate();
                } else if (selectUser == 2) {
                    userDelete();
                } else if (selectUser == 3) {
                    noteIndex();
                } else if (selectUser == 4) {
                    if (loginUno == 1){
                        adminMain();
                    } else {
                        main();
                    }
                } else {
                    System.out.println("[경고] 제시한 번호를 입력해주세요.");
                }
            } // for end
        }catch (Exception e){
            System.out.println(e);
        }

    }



    // 1-1 사용자등록(회원가입)
    // 아이디, 비밀번호, 연락처, 사용자명, 사업자명, 사업자번호, 사업장주소를 입력 받아 DB에 저장한다.
    public void userAdd(){
        // 1. 입력 받기
        System.out.println("=========== 회원 가입 페이지입니다 ============");
        System.out.print("아이디 : ");
        String uid = scan.next();
        System.out.print("비밀번호 : ");
        String upwd = scan.next();
        System.out.print("연락처 : ");
        String uphone = scan.next();
        System.out.print("사용자명 : ");
        String uname = scan.next();
        System.out.print("사업자명 : ");
        scan.nextLine(); // 버퍼 문제 해결 및 공백 방지
        String ubname = scan.nextLine();
        System.out.print("사업자번호 : ");
        String ubnumber = scan.next();
        System.out.print("사업장주소 : ");
        scan.nextLine(); // 버퍼 문제 해결 및 공백 방지
        String ublocation = scan.nextLine();
        System.out.println("=============================================");
        // 2. 컨트롤러에 전달 후 리턴값 저장
        boolean result = userController.userAdd(uid, upwd, uphone, uname, ubname, ubnumber, ublocation);
        // 3. 리턴값 출력
        if (result){
            System.out.println("[안내] 회원가입 성공");
        } else {
            System.out.println("[경고] 회원가입 실패");
        }
        System.out.println("=============================================");
    } // func end

    // 1-2) 사용자정보수정
    //String upwd
    //String uphone
    //String uname
    public void userUpdate(){
        // 1. 입력받기
        System.out.println("수정할 정보를 입력해주세요.");
        System.out.print("비밀번호 확인 : ");
        String upwd = scan.next();  // 기존 비밀번호
        // * 유효성 검사
        boolean check = userController.checkPwd(upwd);
        if (upwd.equals(check)){
            System.out.println("[경고] 비밀번호를 확인해주세요.");
            return;
        }
        // 검사 통과 후
        System.out.print("새 비밀번호 : ");
        String upwdNew = scan.next(); // 신규 비밀번호
        System.out.print("연락처 : ");
        String uphone = scan.next();
        System.out.print("사용자명 : ");
        String uname = scan.next();
        // 2. 컨트롤러에 전달 후 리턴값 저장
        boolean result = userController.userUpdate(upwdNew , uphone , uname);
        // 3. 리턴값 출력
        if (result){
            System.out.println("[안내] 정보 수정 완료");
        } else {
            System.out.println("[경고] 비밀번호를 확인해주세요.");
        }
    } // func end

    // 1-3) 사용자탈퇴
    public void userDelete(){
        // 1. 입력받기
        System.out.print("비밀번호 확인 : ");
        String upwd = scan.next();  // 기존 비밀번호
        // * 유효성 검사
        boolean check = userController.checkPwd(upwd);
        if (!upwd.equals(check)){
            System.out.println("[경고] 비밀번호를 확인해주세요.");
            return;
        }
        System.out.println("정말 삭제하시겠습니까? [Y/N]");
        String deleteCheck = scan.next();
        // * 유효성 검사
        if (deleteCheck != "Y"){
            System.out.println("[안내] 돌아와주셔서 감사합니다.");
            mypageIndex();
            return;
        }
        // 2. 컨트롤러에 전달 후 리턴값 저장
        boolean result = userController.userDelete(loginUno);
        // 3. 리턴값 출력
        if (result){
            System.out.println("[안내] 삭제 완료 되었습니다. 안녕히 가십시오.");
        } else {
            System.out.println("[경고] ");
        }
    }

    // 1-4) 상세사용자조회
    public void userPrint(){
        // 1. 입력받기
        System.out.println("============= 판매자 상세 페이지 =============");
        System.out.println(" 상세조회할 판매자 번호 > ");
        int pno = scan.nextInt();
        // 2. 컨트롤러에 전달 후 리턴값 저장
        ArrayList<UserDto> result = userController.userPrint(pno);
        // 3. 화면 구현
        System.out.println("사업자명 | 사업자번호    | 사업장주소지");
        for (UserDto dto : result){
            System.out.println(dto.getUbname() + "|" + dto.getUbnumber() + "|" + dto.getUblocation());
            System.out.println("☎Tel | " + dto.getUphone());
        }
    }

    // 1-5) 로그인
    // admin일 경우 adminMain, 그 외의 회원일 경우 main으로 이동
    public void login(){
        // 1. 입력받기
        System.out.print("아이디 : ");
        String uid = scan.next();
        System.out.print("비밀번호 : ");
        String upwd = scan.next();
        // 2. 컨트롤러에 전달 후 리턴값 저장
        boolean result = userController.login(uid, upwd);
        // 3. 리턴값 출력
        if (result){
            if (uid.equals("admin")){
                System.out.println("[안내] 관리자 로그인 성공");
                System.out.println(loginUno); // 나중에 삭제
                adminMain();
            } else {
                System.out.println("[안내] 로그인 성공");
                System.out.println(loginUno);
                main();
            }
        } else {
            System.out.println("[경고] 아이디 또는 비밀번호 확인해주세요.");
        }

    } // func end


    // 1-6) 로그아웃
    public void logout(){
        // 2. 컨트롤러에 전달
        userController.logout();
        // 3. 리턴값 출력
        System.out.println("[안내] 로그아웃 되었습니다.");
        index();
    }

    // 2-1) 제품 샘플 등록
    public void sampleAdd(){

    }

    // 2-2) 제품 샘플 삭제
    public void sampleDel(){

    }

    // 2-3) 제품 샘플 조회
    public void samplePrint(){

    }

    // 3-1) 제품 등록
    public void productAdd(){
        samplePrint(); // 제품샘플조회 함수 호출
        // 1. 입력받기
        System.out.println("============= 제품등록 페이지 =============");
        System.out.println("┌──────────────────┐");
        System.out.println("│  추가할 제품 번호 > ");
        System.out.println("└──────────────────┘");
        int sno = scan.nextInt();
        System.out.println("=========================================");
        System.out.print("가격 : ");
        int pprice = scan.nextInt();
        System.out.print("재고 : ");
        int pstock = scan.nextInt();
        System.out.print("상태 : *신품이면 true, 중고이면 false 입력해주세요.*");
        boolean pstatus = scan.nextBoolean();
        // 2. 컨트롤러에 전달 후 리턴값 저장
        boolean result = productController.productAdd(sno, pprice, pstock, pstatus);
        // 3. 리턴값 출력
        if(result){
            System.out.println("[안내] 제품 등록 성공");
        } else {
            System.out.println("[경고] 제품 등록 실패");
        }
    } // func end

    // 3-2) 전체 제품 조회
    public void productPrint(){
        // 1. 입력받기 (없음)
        // 2. 컨트롤러 전달 후 리턴값 저장
        ArrayList<ProductDto> result = productController.productPrint();

        // 3. 화면 구현
        System.out.println("============= 전체제품조회 페이지 =============");
        System.out.printf("번호 \t 판매자 \t 제품정보 \t\t\t\t\t\t 가격");
        for (ProductDto dto : result){
            System.out.printf("%s \t %s \t %s \n", dto.getPno());
        }

    }

    // 3-3) 전체 제품 조회 (판매자 상세 페이지용)
    public void productPrint(int pno) {
        // 1. 입력받기 (pno)


        // 2. 컨트롤러 전달 후 리턴값 저장
        ArrayList<ProductDto> result = productController.productPrint();
        ArrayList<UserDto> result2 = userController.userPrint(pno);
        // 3. 화면구현
        System.out.println("============= 판매자 상세 제품 조회 페이지 =============");

        userPrint(); // 사용자 조회 함수 호출

    } // func end

    // 3-4) 제품 수정
    public void productUpdate(){

    }

    // 3-5) 제품 삭제
    public void productDelete(){

    }

    // 3-6) 장바구니 등록
    public void cartAdd(){

    }

    // 3-7) 장바구니 조회
    public void cartPrint(){

    }

    // 3-8) 장바구니 삭제
    public void cartDelete(){

    }

    // 4*) 쪽지 인덱스
    public void noteIndex(){

    }

    // 4-1) 쪽지 전송
    public void noteAdd(){

    }

    // 4-2) 쪽지 조회
    public void notePrint(){

    }


} // class end
