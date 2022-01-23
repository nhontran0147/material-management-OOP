
package DoiTuong;
import ThaoTacChuoi.ChuyenDoiXau;

import java.util.ArrayList;

public class NhanVien {
    private String maNhanVien;
    private String hoNhanVien;
    private String tenNhanVien;
    private String phaiNhanVien;
    private boolean trangThai;
    private ArrayList<HoaDon> hoaDonArrayList;

    public NhanVien(String maNhanVien,String hoNhanVien,String tenNhanVien,String phaiNhanVien,boolean trangThai){
        this.maNhanVien = maNhanVien;
        this.hoNhanVien = hoNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.phaiNhanVien = phaiNhanVien;
        this.trangThai = trangThai;
        hoaDonArrayList = new ArrayList<>();
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }
    public String getTenNhanVienKhongDau(){
        return ChuyenDoiXau.removeAccent(tenNhanVien);
    }
    public String getHoNhanVienKhongDau(){
        return ChuyenDoiXau.removeAccent(hoNhanVien);
    }
    public String getHoNhanVien() {
        return hoNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getPhaiNhanVien() {
        return phaiNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setHoNhanVien(String hoNhanVien) {
        this.hoNhanVien = hoNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setPhaiNhanVien(String phaiNhanVien) {
        this.phaiNhanVien = phaiNhanVien;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    public void addHoaDon(HoaDon hoaDon){
        hoaDonArrayList.add(hoaDon);
    }

    public ArrayList<HoaDon> getHoaDonArrayList() {
        return new ArrayList<>(hoaDonArrayList);
    }
    public int getSoLuongHoaDon(){
        return hoaDonArrayList.size();
    }

    public Object[] toObjectsSTT(int number){
        if (trangThai)
            return new Object[] {number,maNhanVien,hoNhanVien,tenNhanVien,phaiNhanVien,"1"};
        else
            return new Object[] {number,maNhanVien,hoNhanVien,tenNhanVien,phaiNhanVien,"0"};
    }
}
