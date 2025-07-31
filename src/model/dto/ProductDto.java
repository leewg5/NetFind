package model.dto;

public class ProductDto {
    private int pno, sno, uno;
    private int pprice, pstock;
    private boolean pstatus;

    public ProductDto(int sno, int pprice, int pstock, boolean pstatus) {
        this.sno = sno;
        this.pprice = pprice;
        this.pstock = pstock;
        this.pstatus = pstatus;
    }

    public ProductDto(int pprice, int pstock, boolean pstatus, int pno) {
        this.pprice = pprice;
        this.pstock = pstock;
        this.pstatus = pstatus;
        this.pno = pno;
    }

    public ProductDto(int pno, int sno, int uno, int pprice, int pstock, boolean pstatus) {
        this.pno = pno;
        this.sno = sno;
        this.uno = uno;
        this.pprice = pprice;
        this.pstock = pstock;
        this.pstatus = pstatus;
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

    @Override
    public String toString() {
        return "ProductDto{" +
                "pno=" + pno +
                ", sno=" + sno +
                ", uno=" + uno +
                ", pprice=" + pprice +
                ", pstock=" + pstock +
                ", pstatus=" + pstatus +
                '}';
    }
}
