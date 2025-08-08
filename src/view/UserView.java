package view;

import controller.ProductController;
import controller.UserController;
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
    private ProductController productController = ProductController.getInstance();

    // (*) View 싱글톤 호출
    private ProductView productView = ProductView.getInstance();
    private NoteView noteView = NoteView.getInstance();
    private SampleView sampleView = SampleView.getInstance();

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
                System.out.print("  선택 >    ");
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
                System.out.print("  선택 >     ");
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
                System.out.print("  선택 >    ");
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
                System.out.println("============================ 제품 샘플 페이지 ============================");
                System.out.println("┌────────────────┐ ┌────────────────┐ ┌────────────────┐ ┌────────────┐");
                System.out.println("  1. 제품샘플등록      2. 제품샘플삭제      3. 제품샘플조회      4. 홈 화면  ");
                System.out.println("└────────────────┘ └────────────────┘ └────────────────┘ └────────────┘");
                System.out.println("========================================================================");
                System.out.print("  선택 >    ");
                int selectSample = scan.nextInt();
                if (selectSample == 1) {
                    sampleView.sampleAdd();
                } else if (selectSample == 2) {
                    sampleView.sampleDel();
                } else if (selectSample == 3) {
                    sampleView.samplePrint();
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
                System.out.println("┌─────────────┐ ┌─────────────┐ ┌─────────────┐ ┌─────────────┐ ┌─────────────┐");
                System.out.println("  1. 제품 등록      2. 제품 수정      3. 제품 삭제     4. 제품 조회      5. 홈 화면  ");
                System.out.println("└─────────────┘ └─────────────┘ └─────────────┘ └─────────────┘ └─────────────┘");
                System.out.println("========================================================================");
                System.out.print("  선택 >    ");
                int selectProduct = scan.nextInt();
                if (selectProduct == 1) {
                    productView.productAdd();
                } else if (selectProduct == 2) {
                    productView.productUpdate();
                } else if (selectProduct == 3) {
                    productView.productDelete();
                } else if (selectProduct == 4) {
                    productView.productPrintDB();
                } else if (selectProduct == 5) {
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
                System.out.println("┌─────────────┐ ┌────────────────┐ ┌────────────────┐ ┌────────────────┐ ┌───────────────────┐  ┌───────────┐");
                System.out.println("  0. 빠른 검색      1. 장바구니담기       2. 장바구니확인     3. 장바구니삭제      4. 판매자상세페이지      5. 홈 화면 ");
                System.out.println("└─────────────┘ └────────────────┘ └────────────────┘ └────────────────┘ └───────────────────┘  └───────────┘");
                System.out.println("========================================================================");
                System.out.println("* 판매자상세페이지에서 쪽지를 보낼 수 있습니다.");
                System.out.print("  선택 >    ");
                int selectCart = scan.nextInt();
                if (selectCart == 0){
                    productView.productSearch();
                } else if (selectCart == 1) {
                    productView.cartAdd();
                } else if (selectCart == 2) {
                    productView.cartPrint();
                } else if (selectCart == 3) {
                    productView.cartDelete();
                } else if (selectCart == 4) {
                    productView.productDetailPrint();
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
                System.out.print("  선택 >    ");
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
        } // catch end
    } // func end

    // 4*) 쪽지 인덱스
    public void noteIndex(){
        for( ; ;) {
            try {
                System.out.println("=========================== 쪽지 기능 페이지 ===========================");
                System.out.println("┌─────────────┐ ┌───────────────┐ ┌───────────────┐");
                System.out.println("  1. 쪽지전송      2. 전체쪽지조회    3. 마이 페이지로");
                System.out.println("└─────────────┘ └───────────────┘ └───────────────┘ ");
                System.out.println("=====================================================================");
                System.out.print("  선택 >     ");
                int select = scan.nextInt();
                if (select == 1) {
                    noteView.noteAdd();
                } else if (select == 2) {
                    noteView.notePrint();
                } else if (select == 3) {
                    mypageIndex();
                } else {
                    System.out.println("[경고] 제시한 번호를 입력해주세요.");
                }
            } catch (InputMismatchException e){
                System.out.println("[경고] 정수를 입력해주세요. <다시 입력>");
                scan = new Scanner(System.in);
            } catch (Exception e){
                System.out.println("[오류] 관리자에게 문의해주세요. 010-1234-5678");
            } // catch end
        } // for end
    } // func end

    // 1-1 사용자등록(회원가입)
    // 아이디, 비밀번호, 연락처, 사용자명, 사업자명, 사업자번호, 사업장주소를 입력 받아 DB에 저장한다.
    public void userAdd(){
        try {
            // 1. 입력 받기
            System.out.println("============================ 회원 가입 페이지 ============================");
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
            System.out.println("=======================================================================");
            // 2. 컨트롤러에 전달 후 리턴값 저장
            boolean result = userController.userAdd(uid, upwd, uphone, uname, ubname, ubnumber, ublocation);
            // 3. 리턴값 출력
            if (result) {
                System.out.println("[안내] 회원가입 성공");
            } else {
                System.out.println("[경고] 회원가입 실패");
            }
            System.out.println("=======================================================================");
        }catch (Exception e){
            System.out.println(e);
        }
    } // func end

    // 1-2) 사용자정보수정
    //String upwd
    //String uphone
    //String uname
    public void userUpdate(){
        try {
            // 1. 입력받기
            System.out.println("수정할 정보를 입력해주세요.");
            System.out.print("비밀번호 확인 : ");
            String upwd = scan.next();  // 기존 비밀번호
            // * 유효성 검사
            boolean check = userController.checkPwd(upwd);
            if (!check) {
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
            boolean result = userController.userUpdate(upwdNew, uphone, uname);
            // 3. 리턴값 출력
            if (result) {
                System.out.println("[안내] 정보 수정 완료");
            } else {
                System.out.println("[경고] 정보 수정 실패 (DB 오류)");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    } // func end

    // 1-3) 사용자탈퇴
    public void userDelete(){
        try {
            // 1. 입력받기
            System.out.print("비밀번호 확인 : ");
            String upwd = scan.next();  // 기존 비밀번호
            // * 유효성 검사
            boolean check = userController.checkPwd(upwd);
            if (!check) {
                System.out.println("[경고] 비밀번호를 확인해주세요.");
                return;
            }
            System.out.println("정말 삭제하시겠습니까? [Y/N]");
            String deleteCheck = scan.next();
            // * 유효성 검사
            if (!deleteCheck.equalsIgnoreCase("Y")) { // 대소문자 구분 없이 Y 아니면
                System.out.println("[안내] 탈퇴가 취소되었습니다.");
                return;
            }
            // 2. 컨트롤러에 전달 후 리턴값 저장
            boolean result = userController.userDelete(loginUno);
            // 3. 리턴값 출력
            if (result) {
                System.out.println("[안내] 회원 탈퇴 완료 되었습니다. 안녕히 가십시오.");
                logout();
            } else {
                System.out.println("[경고] 회원 탈퇴 실패 (DB 오류)");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    } // func end

    // 1-5) 로그인
    // admin일 경우 adminMain, 그 외의 회원일 경우 main으로 이동
    public void login(){
        try {
            // 1. 입력받기
            System.out.print("아이디 : ");
            String uid = scan.next();
            System.out.print("비밀번호 : ");
            String upwd = scan.next();
            // 2. 컨트롤러에 전달 후 리턴값 저장
            boolean result = userController.login(uid, upwd);
            // 3. 리턴값 출력
            if (result) {
                if (uid.equals("admin")) {
                    System.out.println("[안내] 관리자 로그인 성공");
                    adminMain();
                } else {
                    System.out.println("[안내] 로그인 성공");
                    main();
                }
            } else {
                System.out.println("[경고] 아이디 또는 비밀번호 확인해주세요.");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    } // func end


    // 1-6) 로그아웃
    public void logout(){
        // 2. 컨트롤러에 전달
        userController.logout();
        // 3. 리턴값 출력
        System.out.println("[안내] 로그아웃 되었습니다.");
        productController.cartDelete(); // 장바구니 삭제
        index();
    }


} // class end
