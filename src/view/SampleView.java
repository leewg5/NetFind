package view;

//import controller.SampleController;

import java.util.Scanner;

public class SampleView {
    // (*)싱글톤
    private SampleView(){}
    private static final SampleView instance = new SampleView();
    public static SampleView getInstance(){
        return instance;
    }
    // (*) 모든 메소드가 사용 가능하도록 메소드밖에 입력객체 생성
    private Scanner scan = new Scanner( System.in );
    // (*) BoardController 싱글톤 불러오기
//    private SampleController sampleController = SampleController.getInstance();

    // (*) 메인 화면 구현
    public void main(){
        for( ;;){
            System.out.println("=========================== Net Find 메인 페이지 ===========================");
            System.out.println("┌────────────────┐ ┌────────────┐ ┌─────────────┐ ┌────────────┐  ┌────────────┐");
            System.out.println("  0. 제품샘플관리      1. 판매등록     2. 조회구매       3. 마이페이지    4. 로그아웃 ");
            System.out.println("└────────────────┘ └────────────┘ └─────────────┘ └────────────┘  └────────────┘");
            System.out.println("==========================================================================");
            System.out.println("┌────────────┐");
            System.out.println("│  선택 >    ");
            System.out.println("└────────────┘");
            int select = scan.nextInt();
        }// for e
    }// func e



}// class e
