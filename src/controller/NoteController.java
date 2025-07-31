package controller;

import model.dao.NoteDao;

public class NoteController {

    private NoteController(){}
    private static final NoteController instance = new NoteController();
    public static NoteController getInstance(){return instance;}

    // 예제 10번 참조
    //(*) noteDao 싱글톤 가져오기
    private NoteDao noteDao = NoteDao.getInstance();
    // (1) 등록 기능 구현
    // public boolean noteAdd(){}




}
