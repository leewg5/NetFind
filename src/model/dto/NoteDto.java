package model.dto;

public class NoteDto {

    //1. 멤버변수 : private 필수로 하고 db테이블 속성과 일치화 *커스텀
    private int nno;
    private int nsend;
    private int nreceive;
    private String ncontext;
    private String ndate;

    public NoteDto(int nreceive, String ncontext) {
        this.nreceive = nreceive;
        this.ncontext = ncontext;
    }

    public NoteDto(int nno, int nsend, int nreceive, String ncontext, String ndate) {
        this.nno = nno;
        this.nsend = nsend;
        this.nreceive = nreceive;
        this.ncontext = ncontext;
        this.ndate = ndate;
    }

    public int getNno() {
        return nno;
    }

    public void setNno(int nno) {
        this.nno = nno;
    }

    public int getNsend() {
        return nsend;
    }

    public void setNsend(int nsend) {
        this.nsend = nsend;
    }

    public int getNreceive() {
        return nreceive;
    }

    public void setNreceive(int nreceive) {
        this.nreceive = nreceive;
    }

    public String getNcontext() {
        return ncontext;
    }

    public void setNcontext(String ncontext) {
        this.ncontext = ncontext;
    }

    public String getNdate() {
        return ndate;
    }

    public void setNdate(String ndate) {
        this.ndate = ndate;
    }

    @Override
    public String toString() {
        return "NoteDto{" +
                "nno=" + nno +
                ", nsend=" + nsend +
                ", nreceive=" + nreceive +
                ", ncontext='" + ncontext + '\'' +
                ", ndate='" + ndate + '\'' +
                '}';
    }
}
