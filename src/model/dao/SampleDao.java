package model.dao;

public class SampleDao {
    // (*)싱글톤
    private SampleDao(){}
    private static final SampleDao instance = new SampleDao();
    public static SampleDao getInstance(){
        return instance;
    }
}// class e
