package DoiTuong;

public class HoTroTop {
    private String maVatTu;
    private String tenVatTu;
    private long soLuongBan;
    private double tienThu;
    public HoTroTop (String maVatTu, String tenVatTu, long soLuongBan, long tienThu){
        this.maVatTu=maVatTu;
        this.tenVatTu=tenVatTu;
        this.soLuongBan=soLuongBan;
        this.tienThu=tienThu;
    }
    public String getMaVatTu(){
        return maVatTu;
    }
    public void addSoLuong(int soLuong){
        this.soLuongBan+= (long)soLuong;
    }
    public void addTienThu(double tienThu){
        this.tienThu+=tienThu;
    }
    public double getTienThu(){
        return tienThu;
    }
    public Object[] toObjects(int stt){
        return new Object[]{stt,maVatTu,tenVatTu,soLuongBan,(long)tienThu};
    }

}
