package DoiTuong;

public class ChiTietHoaDon {
    private String soHoaDon;
    private String maVatTu;
    private int soLuong;
    private float vat;
    private int donGia;

    public ChiTietHoaDon(String soHoaDon, String maVatTu, int soLuong, int donGia, float vat){
        this.soHoaDon=soHoaDon;
        this.maVatTu=maVatTu;
        this.soLuong=soLuong;
        this.vat=vat;
        this.donGia=donGia;
    }

    public void setSoHoaDon(String soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public void setMaVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public String getSoHoaDon() {
        return soHoaDon;
    }


    public String getMaVatTu() {
        return maVatTu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public float getVat() {
        return vat;
    }

    public Object[] toObjects(){
        return new Object[] {soHoaDon, donGia, maVatTu, soLuong, vat};
    }
    public double getTong(){
        double tong=(double) soLuong*(double)donGia*(vat/100.0+1);
        return tong;
    }
}
