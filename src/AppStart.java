import controller.NoteController;

public class AppStart {
    public static void main(String[] args) {

        NoteController.getInstance().noteAdd(2 , "개당 15,000원 입니다.");

        //NoteController.getInstance().notePrint();  for문 돌려서 가져오기.



    } // main end
} // class end
