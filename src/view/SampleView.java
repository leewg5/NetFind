package view;

import controller.SampleController;
import model.dto.SampleDto;
import java.util.ArrayList;
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

    // 2-1) 제품 샘플 등록
    public void sampleAdd(){
        try {
            // 1. 입력 받기
            System.out.println("============================ 제품 샘플 등록 페이지 ============================");
            System.out.print("제품명 : ");
            String sname = scan.next();
            System.out.print("규격 : ");
            String sspec = scan.next();
            System.out.print("제조사 : ");
            String smaker = scan.next();
            System.out.print("단위 : ");
            String sunit = scan.next();
            System.out.println("============================================================================");
            // 2. 컨트롤러에 전달
            boolean result = sampleController.sampleAdd(sname, sspec , smaker , sunit);
            // 3. 리턴값 출력
            if (result) {
                System.out.println("[안내] 샘플 등록 완료");
            } else {
                System.out.println("[경고] 샘플 등록 실패");
            }
        }catch (Exception e){
            System.out.println(e);
        } // catch end
    }  // func end

    // 2-2) 제품 샘플 삭제
    public void sampleDel(){
        System.out.println("=========================== 제품 삭제 페이지 ===========================");
        samplePrint();
        // 1. 입력받기
        System.out.print("삭제할 제품의 번호를 입력 :");
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

    // 2-3) 제품 샘플 조회
    public void samplePrint(){
        // 1. controller에게 요청후 결과받기
        ArrayList<SampleDto> result = sampleController.samplePrint();
        // 2. 결과에 따른 화면구현
        System.out.println(" 번호 \t 제품명 \t 규격 \t 제조사 \t 단위");
        for (SampleDto dto : result) { //향상된 for문, for( 항목타입 변수명 : 리스트명) { }
            System.out.printf(" %s \t %s \t %s \t %s \t %s \n", dto.getSno(), dto.getSname(), dto.getSspec(), dto.getSmaker(), dto.getSunit());
        }
    }


}// class e
