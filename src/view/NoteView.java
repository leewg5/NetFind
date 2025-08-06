package view;

import controller.NoteController;
import controller.UserController;
import model.dto.NoteDto;
import model.dto.UserDto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NoteView {
    // (*) 싱글톤
    private NoteView(){}
    private static final NoteView instance = new NoteView();
    public static NoteView getInstance(){
        return instance;
    }
    private Scanner scan = new Scanner(System.in);

    // (*) 노트 싱글톤, 유저 싱글톤 불러오기
    private UserController userController = UserController.getInstance();
    private NoteController noteController = NoteController.getInstance();

    // 4-1) 쪽지 전송
    public void noteAdd(){
        try {
            // 1. 입력받기
            System.out.println("쪽지를 보낼 판매자를 선택하세요.");
            System.out.println("번호 판매자명 \t 사업자명 \t 연락처 \t\t\t 사업장주소");
            // * 화면구현
            ArrayList<UserDto> list = userController.userPrintDB();
            for (UserDto dto : list ){
                System.out.printf("%s \t %s \t\t %s \t %s \t %s \n" , dto.getUno() , dto.getUname() , dto.getUbname() , dto.getUphone() , dto.getUblocation());
            }
            System.out.print("  선택 >     ");
            int nreceive = scan.nextInt();
            System.out.print("보낼 내용을 적어주세요 :");
            scan.nextLine();
            String ncontext = scan.nextLine();
            // 2. 컨트롤러에 전달 후 리턴값 저장
            boolean result = noteController.noteAdd(nreceive, ncontext);
            // 3. 리턴값 출력
            if( result ) {
                System.out.println("[안내] 쪽지 전송 완료");
            } else {
                System.out.println("[경고] 쪽지 전송 실패");
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
    } // func end

    // 4-2) 쪽지 조회
    public void notePrint(){
        try {
            ArrayList<NoteDto> list = noteController.notePrint();
            System.out.println("번호\t보낸사람\t받는사람\t쪽지내용\t\t\t작성일");

            for (NoteDto dto : list ){
                String senderName = noteController.getUnameByUno(dto.getNsend());
                String receiverName = noteController.getUnameByUno(dto.getNreceive());

                System.out.printf("%s\t%s\t\t%s\t%s\t%s\n",
                        dto.getNno(),
                        senderName,
                        receiverName,
                        dto.getNcontext(),
                        dto.getNdate());
            }
            System.out.println("------------------------------------------------------------------------------");
        } catch (Exception e){
            System.out.println(e);
        }
    }


}
