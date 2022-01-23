package DoiTuong;

import java.util.ArrayList;
import java.util.Date;

public class HoaDon {
    private String soHoaDon;
    private Date ngayLap;
    private String loaiHoaDon;
    private ArrayList<ChiTietHoaDon> chiTiet = new ArrayList<>();
    public HoaDon(String soHoaDon, Date ngayLap, String loaiHoaDon){
        this.soHoaDon = soHoaDon;
        this.ngayLap = ngayLap;
        this.loaiHoaDon = loaiHoaDon;
    }
    public void addChiTiet(ChiTietHoaDon motChiTiet){
        this.chiTiet.add(motChiTiet);
    }
    public ArrayList<ChiTietHoaDon> getChiTiet(){
        return chiTiet;
    }

    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public String getSoHoaDon() {
        return soHoaDon;
    }

    public void setLoaiHoaDon(String loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    public int getSoLuongChiTiet(){
        return chiTiet.size();
    }
    public ArrayList<ChiTietHoaDon> getChiTietHoaDon(){
        return new ArrayList<>(chiTiet);
    }
    public void setSoHoaDon(String soHoaDon) {
        this.soHoaDon = soHoaDon;
    }
    public Object[] toObjects(){
        return new Object[]{soHoaDon,ngayLap,loaiHoaDon};
    }
}
