package controller;

import view.NoteView;

public class SampleController {
    // (*)싱글톤
    private SampleController(){}
    private static final SampleController instance = new SampleController();
    public static SampleController getInstance(){
        return instance;
    }

}// class e
