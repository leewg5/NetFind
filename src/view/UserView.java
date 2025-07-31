package view;

import controller.NoteController;
import controller.ProductController;
import controller.SampleController;
import controller.UserController;

import java.util.Scanner;

public class UserView {
    // (*) 싱글톤
    private UserView(){}
    private static final UserView instance = new UserView();
    public static UserView getInstance() {
        return instance;
    }

    // (*) 컨트롤러 싱글톤 호출(
    private UserController userController = UserController.getInstance();
    private SampleController sampleController = SampleController.getInstance();
    private ProductController productController = ProductController.getInstance();
    private NoteController noteController = NoteController.getInstance();

    // (*) 입력 객체 만들기
    Scanner scan = new Scanner(System.in);

    // (*) 로그인 화면 구현
    public void index(){
        for ( ; ;) {
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
            System.out.println("│ 1. 로그인  │ │ 2. 회원가입 │");
            System.out.println("└───────────┘ └────────────┘");
            System.out.println("========================================================================");
            System.out.println("┌────────────┐");
            System.out.println("│  선택 >    │");
            System.out.println("└────────────┘");
            int select = scan.nextInt();
            if (select == 1) {
                // login();
            } else if (select == 2) {
                userAdd();
            }
        } // for end
    }
    
    // (*) 메인 화면 구현
    public void main(){
        
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

    // 1-5 로그인

}
