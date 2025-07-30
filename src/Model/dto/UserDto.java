package Model.dto;

public class UserDto {
    // 1. 멤버변수
    private int uno;
    private String uid;
    private String upwd;
    private String uphone;
    private String ubname;
    private String ubnumber;
    private String ublocation;

    // 2. 생성자
    // 1) 전체 생성자
    public UserDto(int uno, String uid, String upwd, String uphone, String ubname, String ubnumber, String ublocation) {
        this.uno = uno;
        this.uid = uid;
        this.upwd = upwd;
        this.uphone = uphone;
        this.ubname = ubname;
        this.ubnumber = ubnumber;
        this.ublocation = ublocation;
    }

    // 2) 사용자등록 생성자
    public UserDto(String uid, String upwd, String uphone, String ubname, String ubnumber, String ublocation) {
        this.uid = uid;
        this.upwd = upwd;
        this.uphone = uphone;
        this.ubname = ubname;
        this.ubnumber = ubnumber;
        this.ublocation = ublocation;
    }

    // 3) 사용자정보수정 생성자

    public UserDto(String uphone, String ubname) {
        this.uphone = uphone;
        this.ubname = ubname;
    }


    // 3. 메소드


}
