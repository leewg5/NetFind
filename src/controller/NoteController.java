package controller;

public class NoteController {

    private NoteController(){}
    private static final NoteController instance = new NoteController();
    public static NoteController getInstance(){return instance;}

    //(*) noteDao 싱글톤 가져오기



}
