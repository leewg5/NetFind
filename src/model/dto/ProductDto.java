package model.dto;

public class ProductDto {
    private int pno, sno, uno;
    private int pprice, pstock;
    private boolean pstatus;
    private String sname, sspec, smaker, sunit;

    // productAdd 에서 사용하는 생성자
    public ProductDto(int sno, int pprice, int pstock, boolean pstatus) {
        this.sno = sno;
        this.pprice = pprice;
        this.pstock = pstock;
        this.pstatus = pstatus;
    }

    // productPrint 에서 사용하는 생성자
    public ProductDto(int pno, String sname, String sspec, String smaker, String sunit, int pprice, int pstock, boolean pstatus) {
        this.pno = pno;
        this.sname = sname;
        this.sspec = sspec;
        this.smaker = smaker;
        this.sunit = sunit;
        this.pprice = pprice;
        this.pstock = pstock;
        this.pstatus = pstatus;
    }

    // productUpdate 에서 사용하는 생성자
    public ProductDto(int pprice, int pstock, boolean pstatus, int pno) {
        this.pprice = pprice;
        this.pstock = pstock;
        this.pstatus = pstatus;
        this.pno = pno;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getUno() {
        return uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public int getPprice() {
        return pprice;
    }

    public void setPprice(int pprice) {
        this.pprice = pprice;
    }

    public int getPstock() {
        return pstock;
    }

    public void setPstock(int pstock) {
        this.pstock = pstock;
    }

    public boolean isPstatus() {
        return pstatus;
    }

    public void setPstatus(boolean pstatus) {
        this.pstatus = pstatus;
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
        return "ProductDto{" +
                "pno=" + pno +
                ", sno=" + sno +
                ", uno=" + uno +
                ", pprice=" + pprice +
                ", pstock=" + pstock +
                ", pstatus=" + pstatus +
                ", sname='" + sname + '\'' +
                ", sspec='" + sspec + '\'' +
                ", smaker='" + smaker + '\'' +
                ", sunit='" + sunit + '\'' +
                '}';
    }
}
