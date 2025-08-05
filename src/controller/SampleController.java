package controller;

import model.dao.SampleDao;
import model.dto.SampleDto;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class SampleController {
    // (*)싱글톤
    private SampleController(){}
    private static final SampleController instance = new SampleController();
    public static SampleController getInstance(){
        return instance;
    }

    // (*) Controller는 Dao만 호출 할 수있다. dao 싱글톤 호출
    private SampleDao sampleDao = SampleDao.getInstance();

    // (1) 샘플 등록기능구현
    public boolean sampleAdd(String sname , String sspec , String smaker , String sunit ){
        // 1. 유효성 검사( 패스 )
        // 2. 묶음(객체) 하나로 만들기
        SampleDto sampleDto = new SampleDto( 0 ,sname , sspec , smaker , sunit );
        // 3. 객체화된 dto를 dao에게 전달후 결과를 받는다.
        boolean result = sampleDao.sampleAdd( sampleDto );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }// func e

    // (2) 샘플 삭제기능 구현
    public boolean sampleDel( int sno ){
        // 1. 유효성검사  // 2. 객체화
        // 3. dao에게 삭제할 번호 전달후 결과를 받는다.
        boolean result = sampleDao.sampleDel(sno);
        // 4. 결과를 view에게 리턴한다.
        return result;

    }

    // (3) 샘플 조회기능 구현
    public ArrayList<SampleDto> samplePrint(){
        // - 유효성검사 ~ // - 매개변수~
        // 3. dao에게 요청후 결과받기
        ArrayList<SampleDto> result = sampleDao.samplePrint();
        // 4. 결과를 view에게 리턴한다.
        return result;
    }










}// class e
