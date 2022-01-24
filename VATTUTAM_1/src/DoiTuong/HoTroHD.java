package DoiTuong;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HoTroHD {
    private String soHoaDon;
    private Date ngayLap;
    private String loaiHoaDon;
    private double triGia;
    private String hoTenNV;
    public HoTroHD(String soHoaDon,Date ngayLap, String loaiHoaDon, String hoTenNV, double triGia){
        this.soHoaDon=soHoaDon;
        this.ngayLap=ngayLap;
        this.loaiHoaDon=loaiHoaDon;
        this.hoTenNV=hoTenNV;
        this.triGia=triGia;
    }
    public Date getNgayLap() {
        return ngayLap;
    }
    public String getSoHoaDon() {
        return soHoaDon;
    }
    public Object[] toObjects(int stt){
        SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
        String ngLap = fm.format(ngayLap);
        return new Object[]{stt,soHoaDon,ngLap,loaiHoaDon,hoTenNV,(long)triGia};
    }
}
