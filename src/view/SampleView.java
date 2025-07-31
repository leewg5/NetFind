package view;

public class SampleView {
    // (*)싱글톤
    private SampleView(){}
    private static final SampleView instance = new SampleView();
    public static SampleView getInstance(){
        return instance;
    }


}// class e
