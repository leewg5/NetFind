package view;

import Model.NoteDao.NoteDao;

public class NoteView {

    private NoteView(){}
    private static final NoteView instance = new NoteView();
    public static NoteView getInstance(){
        return instance;
    }



}
