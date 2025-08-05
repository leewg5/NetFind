package view;

import controller.SampleController;
import model.dao.SampleDao;
import model.dto.SampleDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SampleView {
    // (*)싱글톤
    private SampleView() {
    }

    private static final SampleView instance = new SampleView();

    public static SampleView getInstance() {
        return instance;
    }

    // (*) 모든 메소드가 사용 가능하도록 메소드밖에 입력객체 생성
    private Scanner scan = new Scanner(System.in);

    // (*) SampleController 싱글톤 불러오기
    private SampleController sampleController = SampleController.getInstance();

    // (*) 메인 화면 구현
    public void index() {
        for (; ; ) {
            try {
                System.out.println("=========================== Net Find 메인 페이지 ===========================");
                System.out.println("┌────────────────┐ ┌────────────┐ ┌─────────────┐ ┌────────────┐  ┌────────────┐");
                System.out.println("  0. 제품샘플관리      1. 판매등록     2. 조회구매       3. 마이페이지    4. 로그아웃 ");
                System.out.println("└────────────────┘ └────────────┘ └─────────────┘ └────────────┘  └────────────┘");
                System.out.println("==========================================================================");
                System.out.println("┌────────────┐");
                System.out.println("│  선택 >    ");
                System.out.println("└────────────┘");
                int choose = scan.nextInt();


                if (choose == 0) {
                    System.out.println("=========================== 제품 샘플 페이지 ===========================");
                    System.out.println("┌────────────────┐ ┌──────────────┐ ┌───────────────┐ ┌────────────┐");
                    System.out.println("  1. 제품샘플등록      2. 제품샘플삭제     3. 제품샘플조회    4. 홈 화면");
                    System.out.println("└────────────────┘ └──────────────┘ └───────────────┘ └────────────┘");
                    System.out.println("======================================================================");
                    int choose2 = scan.nextInt();
                    if (choose2 == 1) {
                        sampleAdd();
                    } else if (choose2 == 2) {
                        sampleDel();
                    } else if (choose2 == 3) {
                        samplePrint();
                    } else if(choose2 == 4) {  }
                    else {
                        System.out.println("[경고] 존재하지 않는 번호 입니다. ");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력타입이 일치하지 않습니다. <다시입력> ");
                scan = new Scanner(System.in);
            } catch (Exception e) {
                System.out.println("[오류] 관리자에게 문의하기 ");
            }// for e
        }// func e
    }

    // (1) 등록기능 구현
    public boolean sampleAdd() {
        // 1. 입력받기
        scan.nextLine();
        System.out.println("제품명 : ");
        String sname = scan.nextLine();
        System.out.println("규격 : ");
        String sspec = scan.nextLine();
        System.out.println("제조사 : ");
        String smaker = scan.nextLine();
        System.out.println("단위 : ");
        String sunit = scan.nextLine();
        // 2. controller 전달하기 // 3. 전달후 (결과)리턴값 저장하기
        boolean result = sampleController.sampleAdd(sname, sspec, smaker, sunit);
        if (result) {
            System.out.println("[안내] 샘플 등록 완료");
        } else {
            System.out.println("[경고] 샘플 등록 불가 ");
        }
        return result;
    }

    // (2) 삭제 기능 구현
    public void sampleDel() {
        System.out.println("=========================== 제품 삭제 페이지 ===========================");
        //samplePrint();
        // 1. 입력받기
        System.out.println("삭제할 제품의 번호를 입력 :");
        int sno = scan.nextInt();
        // 2. controller 전달하기 // 3. 전달 후 (결과)리턴값 저장하기
        boolean result = sampleController.sampleDel(sno);
        // 4. 리턴된 값에 따른 출력하기
        if (result) {
            System.out.println("[안내] 샘플 삭제 완료 ");
        } else {
            System.out.println("[경고] 없는 번호 이거나 실패 ");
        }
    }

    // (3) 전체조회 화면 구현
    public void samplePrint() {
        // 1. controller에게 요청후 결과받기
        ArrayList<SampleDto> result = sampleController.samplePrint();
        // 2. 결과에 따른 화면구현
        System.out.println(" no \t 제품명 \t 규격 \t 제조사 \t 단위 ");
        for (SampleDto dto : result) { //향상된 for문, for( 항목타입 변수명 : 리스트명) { }
            System.out.printf(" %s \t %s \t %s \t %s \t %s \n ", dto.getSno(), dto.getSname(), dto.getSspec(), dto.getSmaker(), dto.getSunit());
        }
    }


}// class e
