package DoiTuong;
import java.lang.*;
import ThaoTacChuoi.ChuyenDoiXau;
public class VatTu {
    private String maVatTu;
    private String tenVatTu;
    private int soLuongTon;
    private String donViTinh;

    public VatTu(String maVatTu, String tenVatTu, int soLuongTon, String donViTinh){
        this.maVatTu = maVatTu;
        this.tenVatTu = tenVatTu;
        this.soLuongTon = soLuongTon;
        this.donViTinh = donViTinh;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
    public String getTenVatTuKhongDau(){
       return ChuyenDoiXau.removeAccent(tenVatTu);
    }
    public void setTenVatTu(String tenVatTu){
        this.tenVatTu = tenVatTu;
    }
    public void setDonViTinh(String donViTinh){
        this.donViTinh = donViTinh;
    }
    public String getMaVatTu(){
        return maVatTu;
    }
    public String getTenVatTu(){
        return tenVatTu;
    }
    public int getSoLuongTon(){
        return soLuongTon;
    }
    public String getDonViTinh(){
        return donViTinh;
    }
    public Object[] toObjects(){
        return new Object[] {maVatTu,tenVatTu,soLuongTon,donViTinh};
    }
    public Object[] toObjectsSTT(int stt){
        return new Object[] {stt,maVatTu,tenVatTu,soLuongTon,donViTinh};
    }
}