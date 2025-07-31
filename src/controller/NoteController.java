package controller;

import model.dao.NoteDao;
import model.dto.NoteDto;

import java.util.ArrayList;

public class NoteController {

    private NoteController(){}
    private static final NoteController instance = new NoteController();
    public static NoteController getInstance(){return instance;}

    //(*) noteDao 싱글톤 가져오기
    private NoteDao noteDao = NoteDao.getInstance();

    //(1) 등록 기능 구현
    public boolean noteAdd(int nreceive, String ncontext){
        //1. 유효성검사는 제외..
        //2. 데이터 문제 없으면 묶음(객체)하나로 만들기
        //*--> 주의할점 매개변수와 동일한 생성자가 존재하지 않으면 오류 발생한다.
        NoteDto noteDto = new NoteDto(nreceive, ncontext);
        //3.객체화 된 dto를 dao에게 전달 후 결과를 받는다.
        boolean result = noteDao.noteAdd(noteDto);
        //4. 결과를 view에게 리턴한다.
        return result;

    }

    //(2) 전체조회 기능 구현
    public ArrayList<NoteDto> notePrint(){
        // - 유효성검사 ~ // - 매개변수 ~
        // 3. dao에게 전달후 결과를 받는다.
        ArrayList<NoteDto> result = noteDao.notePrint();
        //4. 결과를 view에게 리턴한다.
        return result;

    }


}
