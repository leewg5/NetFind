package model.dto;

public class SampleDto {
    // 1. 멤버변수
    private int sno;
    private String sname;
    private String sspec;
    private String smaker;
    private String sunit;

    public SampleDto() {
    }

    public SampleDto(int sno, String sname, String sspec, String smaker, String sunit) {
        this.sno = sno;
        this.sname = sname;
        this.sspec = sspec;
        this.smaker = smaker;
        this.sunit = sunit;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSspec() {
        return sspec;
    }

    public void setSspec(String sspec) {
        this.sspec = sspec;
    }

    public String getSmaker() {
        return smaker;
    }

    public void setSmaker(String smaker) {
        this.smaker = smaker;
    }

    public String getSunit() {
        return sunit;
    }

    public void setSunit(String sunit) {
        this.sunit = sunit;
    }

    @Override
    public String toString() {
        return "SampleDto{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", sspec='" + sspec + '\'' +
                ", smaker='" + smaker + '\'' +
                ", sunit='" + sunit + '\'' +
                '}';
    }
}
