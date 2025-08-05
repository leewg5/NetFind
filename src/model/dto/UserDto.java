package model.dto;

public class UserDto {
    // 1. 멤버변수
    private int uno;
    private String uid;
    private String upwd;
    private String uphone;
    private String uname;
    private String ubname;
    private String ubnumber;
    private String ublocation;

    // 2. 생성자
    // 2-1) 전체 생성자
    public UserDto(int uno, String uid, String upwd, String uphone, String uname, String ubname, String ubnumber, String ublocation) {
        this.uno = uno;
        this.uid = uid;
        this.upwd = upwd;
        this.uphone = uphone;
        this.uname = uname;
        this.ubname = ubname;
        this.ubnumber = ubnumber;
        this.ublocation = ublocation;
    }

    // 2-2) 사용자등록 생성자
    public UserDto(String uid, String upwd, String uphone, String uname, String ubname, String ubnumber, String ublocation) {
        this.uid = uid;
        this.upwd = upwd;
        this.uphone = uphone;
        this.uname = uname;
        this.ubname = ubname;
        this.ubnumber = ubnumber;
        this.ublocation = ublocation;
    }

    // 2-3) 사용자정보수정 생성자
    public UserDto(String upwd, String uphone, String uname) {
        this.upwd = upwd;
        this.uphone = uphone;
        this.uname = uname;
    }

    // 2-4) 비밀번호체크 생성자
    public UserDto(String upwd) {
        this.upwd = upwd;
    }

    // 2-5) 매개변수 없이 빈 생성자
    public UserDto() {
    }

    // 3. 메소드 getter and setter , toString
    public int getUno() {
        return uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUbname() {
        return ubname;
    }

    public void setUbname(String ubname) {
        this.ubname = ubname;
    }

    public String getUbnumber() {
        return ubnumber;
    }

    public void setUbnumber(String ubnumber) {
        this.ubnumber = ubnumber;
    }

    public String getUblocation() {
        return ublocation;
    }

    public void setUblocation(String ublocation) {
        this.ublocation = ublocation;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "uno=" + uno +
                ", uid='" + uid + '\'' +
                ", upwd='" + upwd + '\'' +
                ", uphone='" + uphone + '\'' +
                ", uname='" + uname + '\'' +
                ", ubname='" + ubname + '\'' +
                ", ubnumber='" + ubnumber + '\'' +
                ", ublocation='" + ublocation + '\'' +
                '}';
    }
} // class end
