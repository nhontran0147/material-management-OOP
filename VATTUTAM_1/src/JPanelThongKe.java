import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JPanelThongKe extends JPanel implements ActionListener {

    private JPanel boxVatTu;
    private JPanel boxNhanVien;
    private JPanel boxHoaDon;
    private JLabel lbTKVatTu;
    private static JLabel lbSLVatTu;
    private JLabel lbTKNhanVien;
    private static JLabel lbSLNhanVien;
    private  JLabel lbTKHoaDon;
    private static JLabel lbSLHoaDon;

    private JLabel titleTK;
    private JLabel tgBatDau;
    private JDateChooser datebd;
    private JLabel tgKetThuc;
    private JDateChooser datekt;
    private JLabel lbchonKieuIn;
    private JButton inTheoHD;
    private JButton inTheoDT;
    private JButton xuLyIn;
    private static DefaultTableModel modelHD;
    private static DefaultTableModel modelDT;
    private JPanel pnHoaDon;
    private JTable tbHoaDon;
    private JPanel pnDoanhThu;
    private JTable tbDoanhThu;
    private int inkieu;

    JPanelThongKe(){
//        this.setBackground(new Color(102, 195, 239));
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);
        inkieu=-1;
        ImageIcon iconVT = new ImageIcon(JPanelThongKe.class.getResource("Image/book.png"));
        Image img_one = iconVT.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        lbTKVatTu = new JLabel("Vật Tư");
        lbTKVatTu.setHorizontalAlignment(JLabel.CENTER);
        lbTKVatTu.setHorizontalTextPosition(JLabel.RIGHT);
        lbTKVatTu.setVerticalTextPosition(JLabel.BOTTOM);
        lbTKVatTu.setFont(new Font("Ubuntu", Font.BOLD, 12));
        lbTKVatTu.setForeground(new Color(255,255,255));
        iconVT.setImage(img_one);
        lbTKVatTu.setIcon(new NoScalingIcon(iconVT));
        lbTKVatTu.setBounds(0, 10, 150, 100);

        lbSLVatTu = new JLabel();
        String slVatTu = String.valueOf(GiaoDienQuanLy.getSoLuongVatTu());
        lbSLVatTu.setText(slVatTu);
        lbSLVatTu.setFont(new Font("Ubuntu", Font.BOLD, 25));
        lbSLVatTu.setBounds(180, 20, 60, 100);

        boxVatTu = new JPanel();
        boxVatTu.setLayout(null);
        boxVatTu.setBackground(new Color(252, 86, 88));
        boxVatTu.setBounds(0, 30, 250, 100);
        boxVatTu.add(lbTKVatTu);
        boxVatTu.add(lbSLVatTu);


        ImageIcon iconNV = new ImageIcon(JPanelThongKe.class.getResource("Image/book.png"));
        Image img_two = iconNV.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        lbTKNhanVien = new JLabel("Nhân Viên");
        lbTKNhanVien.setHorizontalAlignment(JLabel.CENTER);
        lbTKNhanVien.setHorizontalTextPosition(JLabel.RIGHT);
        lbTKNhanVien.setVerticalTextPosition(JLabel.BOTTOM);
        lbTKNhanVien.setFont(new Font("Ubuntu", Font.BOLD, 12));
        lbTKNhanVien.setForeground(new Color(255,255,255));
        iconNV.setImage(img_two);
        lbTKNhanVien.setIcon(new NoScalingIcon(iconNV));
        lbTKNhanVien.setBounds(0, 10, 150, 100);

        lbSLNhanVien = new JLabel();
        String slNhanVien = String.valueOf(GiaoDienQuanLy.getSoLuongNhanVien());
        lbSLNhanVien.setText(slNhanVien);
        lbSLNhanVien.setFont(new Font("Ubuntu", Font.BOLD, 25));
        lbSLNhanVien.setBounds(180, 20, 60, 100);

        boxNhanVien = new JPanel();
        boxNhanVien.setLayout(null);
        boxNhanVien.setBackground(new Color(250, 180, 59));
        boxNhanVien.setBounds(350, 30, 250, 100);
        boxNhanVien.add(lbTKNhanVien);
        boxNhanVien.add(lbSLNhanVien);


        ImageIcon iconHD = new ImageIcon(JPanelThongKe.class.getResource("Image/book.png"));
        Image img_three = iconNV.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        lbTKHoaDon = new JLabel("Hóa Đơn");
        lbTKHoaDon.setHorizontalAlignment(JLabel.CENTER);
        lbTKHoaDon.setHorizontalTextPosition(JLabel.RIGHT);
        lbTKHoaDon.setVerticalTextPosition(JLabel.BOTTOM);
        lbTKHoaDon.setFont(new Font("Ubuntu", Font.BOLD, 12));
        lbTKHoaDon.setForeground(new Color(255,255,255));
        iconHD.setImage(img_three);
        lbTKHoaDon.setIcon(new NoScalingIcon(iconHD));
        lbTKHoaDon.setBounds(0, 10, 150, 100);

        lbSLHoaDon = new JLabel();
        String slHoaDon= String.valueOf(GiaoDienQuanLy.getSoLuongHoaDon());
        lbSLHoaDon.setText(slHoaDon);
        lbSLHoaDon.setFont(new Font("Ubuntu", Font.BOLD, 25));
        lbSLHoaDon.setBounds(180, 20, 60, 100);

        boxHoaDon = new JPanel();
        boxHoaDon.setLayout(null);
        boxHoaDon.setBackground(new Color(121, 195, 70));
        boxHoaDon.setBounds(690, 30, 250, 100);
        boxHoaDon.add(lbTKHoaDon);
        boxHoaDon.add(lbSLHoaDon);


        titleTK = new JLabel("THỐNG KÊ HÓA ĐƠN THEO NGÀY VÀ TOP DOANH THU");
        titleTK.setBounds(80, 150, 780, 40);
        titleTK.setBackground(new Color(96, 100, 191));
        titleTK.setForeground(new Color(255,255,255));
        titleTK.setFont(new Font("Ubuntu", Font.BOLD, 25));
        titleTK.setHorizontalAlignment(SwingConstants.CENTER);
        titleTK.setOpaque(true);

        tgBatDau = new JLabel("Chọn thời gian bắt đầu: ");
        tgBatDau.setBounds(80, 220, 150, 30);

        datebd = new JDateChooser(Calendar.getInstance().getTime());
        datebd.setDateFormatString("dd/MM/yyyy");
        datebd.setFont(new Font("Arial", Font.PLAIN, 15));
        datebd.setBounds(250, 220, 120, 30);


        tgKetThuc = new JLabel("Chọn thời gian kết thúc");
        tgKetThuc.setBounds(520, 220, 150, 30);

        datekt = new JDateChooser(Calendar.getInstance().getTime());
        datekt.setFont(new Font("Arial", Font.PLAIN, 15));
        datekt.setBounds(690, 220, 150, 30);
        datekt.setDateFormatString("dd/MM/yyyy");

        lbchonKieuIn = new JLabel("Chọn kiểu in: ");
        lbchonKieuIn.setBounds(80, 300, 150, 30);


        inTheoHD = new JButton("IN THEO HÓA ĐƠN");
        inTheoHD.setBounds(250, 300, 180, 30);
        inTheoHD.setBackground(new Color(255, 85, 85));
        inTheoHD.setFocusable(false);
        inTheoHD.setForeground(Color.WHITE);
        inTheoHD.addActionListener(this);
        inTheoHD.setBorderPainted(false);
        inTheoHD.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inTheoDT = new JButton("IN THEO DOANH THU");
        inTheoDT.setBounds(450, 300, 180, 30);
        inTheoDT.setBackground(new Color(255, 85, 85));
        inTheoDT.setBorderPainted(false);
        inTheoDT.setFocusable(false);
        inTheoDT.setForeground(Color.WHITE);
        inTheoDT.addActionListener(this);
        inTheoDT.setCursor(new Cursor(Cursor.HAND_CURSOR));


        xuLyIn = new JButton("IN DANH SÁCH");
        xuLyIn.setBounds(680, 300, 180, 30);
        xuLyIn.setFocusable(false);
        xuLyIn.setForeground(Color.WHITE);
        xuLyIn.addActionListener(this);
        xuLyIn.setBorderPainted(false);
        xuLyIn.setBackground(new Color(102, 195, 239));
        xuLyIn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // TB HÓA ĐƠN
        String[] columnJT = {"STT", "Số HD", "NGÀY LẬP", "LOẠI HD", "NHÂN VIÊN LẬP", "TRỊ GIÁ HD"};
        Object[][] dataJT = {};
        modelHD = new DefaultTableModel(dataJT, columnJT);
        tbHoaDon = new JTable(modelHD);
        tbHoaDon.setFillsViewportHeight(true);
        tbHoaDon.setBackground(new Color(248, 249, 250));
        tbHoaDon.setOpaque(true);
        tbHoaDon.setShowGrid(false); // Tắt kẻ bảng
        tbHoaDon.setShowHorizontalLines(false); // Tắt kẻ ngang
        tbHoaDon.setShowVerticalLines(false); // Tắt kẻ dọc
        tbHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 15));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbHoaDon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Chữ ra giữa bảng
        tbHoaDon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbHoaDon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbHoaDon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbHoaDon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbHoaDon.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        tbHoaDon.getColumnModel().getColumn(0).setMaxWidth(70);
        tbHoaDon.getColumnModel().getColumn(0).setPreferredWidth(70);
        tbHoaDon.getColumnModel().getColumn(3).setPreferredWidth(10);
        tbHoaDon.getColumnModel().getColumn(2).setPreferredWidth(20);

        tbHoaDon.setRowHeight(30);
        tbHoaDon.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16)); // Set font Header
        tbHoaDon.getTableHeader().setPreferredSize(new Dimension(100, 40)); // Set height
        tbHoaDon.getTableHeader().setBackground(new Color(247, 249, 252));
        tbHoaDon.getTableHeader().setForeground(new Color(105, 121, 141));
        tbHoaDon.setSelectionBackground(Color.YELLOW);
        tbHoaDon.setBackground(new Color(255, 255, 255));
        tbHoaDon.setForeground(new Color(84, 101, 121));
        tbHoaDon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JScrollPane JS = new JScrollPane(tbHoaDon);
        JS.setPreferredSize(new Dimension(1, 100));
        JS.setBounds(3, 0, 950, 300); // w: 700 bg: 100
        tbHoaDon.setFocusable(false); // Không tạo focus 1 phần tử
        tbHoaDon.setDefaultEditor(Object.class, null); // Không cho edit table

        pnHoaDon = new JPanel();
        pnHoaDon.setBackground(new Color(255,255,255));
        pnHoaDon.setBounds(0, 350, 1000, 500);
        pnHoaDon.setLayout(null);
        pnHoaDon.add(JS);


        // TB DOANH THU
        String[] columnJT1 = {"STT", "MÃ VẬT TƯ", "TÊN VẬT TƯ", "SỐ LƯỢNG BÁN", "TIỀN THU"};
        Object[][] dataJT1 = {{1000, "6557", "Xi Măng", "N", 2000000, 1234567890}};
        modelDT = new DefaultTableModel(dataJT1, columnJT1);
        tbDoanhThu = new JTable(modelDT);
        tbDoanhThu.setFillsViewportHeight(true);
        tbDoanhThu.setBackground(new Color(248, 249, 250));
        tbDoanhThu.setOpaque(true);
        tbDoanhThu.setShowGrid(false); // Tắt kẻ bảng
        tbDoanhThu.setShowHorizontalLines(false); // Tắt kẻ ngang
        tbDoanhThu.setShowVerticalLines(false); // Tắt kẻ dọc
        tbDoanhThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbDoanhThu.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Chữ ra giữa bảng
        tbDoanhThu.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbDoanhThu.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbDoanhThu.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbDoanhThu.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tbDoanhThu.getColumnModel().getColumn(0).setMaxWidth(70);
        tbDoanhThu.getColumnModel().getColumn(0).setPreferredWidth(70);
        tbDoanhThu.getColumnModel().getColumn(3).setPreferredWidth(10);
        tbDoanhThu.getColumnModel().getColumn(2).setPreferredWidth(20);

        tbDoanhThu.setRowHeight(30);
        tbDoanhThu.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16)); // Set font Header
        tbDoanhThu.getTableHeader().setPreferredSize(new Dimension(100, 40)); // Set height
        tbDoanhThu.getTableHeader().setBackground(new Color(247, 249, 252));
        tbDoanhThu.getTableHeader().setForeground(new Color(105, 121, 141));
        tbDoanhThu.setSelectionBackground(Color.YELLOW);
        tbDoanhThu.setBackground(new Color(255, 255, 255));
        tbDoanhThu.setForeground(new Color(84, 101, 121));
        tbDoanhThu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JScrollPane JS1 = new JScrollPane(tbDoanhThu);
        JS1.setPreferredSize(new Dimension(1, 100));
        JS1.setBounds(3, 0, 950, 300); // w: 700 bg: 100
        tbDoanhThu.setFocusable(false); // Không tạo focus 1 phần tử
        tbDoanhThu.setDefaultEditor(Object.class, null); // Không cho edit table

        pnDoanhThu = new JPanel();
        pnDoanhThu.setBackground(new Color(255,255,255));
        pnDoanhThu.setBounds(0, 350, 1000, 500);
        pnDoanhThu.setLayout(null);
        pnDoanhThu.add(JS1);
        pnDoanhThu.setVisible(false);


        this.add(boxVatTu);
        this.add(boxNhanVien);
        this.add(boxHoaDon);
        this.add(titleTK);
        this.add(tgBatDau);
        this.add(datebd);
        this.add(tgKetThuc);
        this.add(datekt);
        this.add(lbchonKieuIn);
        this.add(inTheoHD);
        this.add(inTheoDT);
        this.add(xuLyIn);
        this.add(pnHoaDon);
        this.add(pnDoanhThu);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inTheoDT){
            inTheoDT.setBackground(new Color(42, 185, 48));
            inTheoHD.setBackground(new Color(255, 85, 85));
            inkieu = 2;
        }
        if(e.getSource() == inTheoHD){
            inTheoHD.setBackground(new Color(42, 185, 48));
            inTheoDT.setBackground(new Color(255, 85, 85));
            inkieu = 1;
        }

        if(e.getSource() == xuLyIn){
            if(datebd.getDate() == null){
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu không hợp lệ");
                return;
            }
            if(datekt.getDate() == null){
                JOptionPane.showMessageDialog(null, "Ngày kết thúc không hợp lệ");
                return;
            }
            SimpleDateFormat fm1 = new SimpleDateFormat("yyyy/MM/dd");
            String timeToDay=fm1.format(Calendar.getInstance().getTime());
            String[] arrTD = timeToDay.split("/");
            String timebd = fm1.format(datebd.getDate());
            String[] arrBD=timebd.split("/");
            String timekt = fm1.format(datekt.getDate());
            String[] arrKT = timekt.split("/");
            for (int i=0;i<arrBD.length;++i){
                if (Integer.parseInt(arrBD[i])>Integer.parseInt(arrTD[i])){
                    JOptionPane.showMessageDialog(null, "Thời gian bắt đầu không được lớn hơn thời gian hiện tại!");
                    return;
                }else if (Integer.parseInt(arrBD[i])<Integer.parseInt(arrTD[i])) break;
            }
            for (int i=0;i<arrKT.length;++i){
                if (Integer.parseInt(arrKT[i])>Integer.parseInt(arrTD[i])){
                    JOptionPane.showMessageDialog(null, "Thời gian kết thúc không được lớn hơn thời gian hiện tại!");
                    return;
                }else if (Integer.parseInt(arrKT[i])<Integer.parseInt(arrTD[i])) break;
            }

            for (int i=0;i<arrBD.length;++i){
                if (Integer.parseInt(arrKT[i])-Integer.parseInt(arrBD[i])<0){
                    JOptionPane.showMessageDialog(null, "Thời gian bắt đầu không được lớn hơn thời gian kết thúc");
                    return;
                }else if (Integer.parseInt(arrKT[i])-Integer.parseInt(arrBD[i])>0)
                    break;
            }
            if(inkieu == -1){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn kiểu in");
                return;
            }
            SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
            String time_one = fm.format(datebd.getDate());
            String timetwo = fm.format(datekt.getDate());
            if(inkieu == 1){
                pnDoanhThu.setVisible(false);
                pnHoaDon.setVisible(true);
//                String sql = "SELECT * FROM HOADON AS HD, (SELECT CTHOADON.SoHD, NV.HoNV, NV.TenNV, " +
//                        "SUM(CTHOADON.DonGia * CTHOADON.SoLuong *(CTHOADON.Vat / 100 + 1)) AS TONG FROM CTHOADON, NHANVIEN AS NV " +
//                "WHERE CTHOADON.MaNV = NV.MaNV "+
//                "GROUP BY SoHD, NV.HoNV, NV.TenNV) AS CT " +
//                "WHERE HD.SoHD = CT.SoHD AND HD.NgayLap BETWEEN '" + time_one +"' AND '"+ timetwo +"'" + " ORDER BY HD.NgayLap";
//                //System.out.println("SQL = "+sql);
                modelHD.setRowCount(0);
                //System.out.println("cham hoi");
//                String[] columnJT = {"STT", "Số HD", "NGÀY LẬP", "LOẠI HD", "NHÂN VIÊN LẬP", "TRỊ GIÁ HD"};
                GiaoDienQuanLy.napModelThongKeHoaDon(modelHD, time_one, timetwo);
//                try {
//                    PreparedStatement pre = ConnectSQL.getCon().prepareStatement(sql);
//                    ResultSet rs = pre.executeQuery();
//                    int stt = 1;
//                    while (rs.next()) {
//                        modelHD.addRow(new Object[]{stt, rs.getInt("SoHD"), rs.getDate("NgayLap"),
//                                rs.getString("Loai"), rs.getString("HoNV") + " "+  rs.getString("TenNV"),
//                                ChuanHoa.ChuyenSoThanhTien(String.valueOf(rs.getInt("Tong")))});
//                        stt ++;
//                    }
//                    pre.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
            }
            if(inkieu == 2){
                pnHoaDon.setVisible(false);
                pnDoanhThu.setVisible(true);
//                String sql = "SELECT TOP 10 VT.MaVT, VT.TenVT, SUM(CT.SoLuong) AS SLBAN, SUM((CT.DonGia * CT.SoLuong) * (CT.Vat / 100 + 1)) AS TONG FROM CTHOADON AS CT, HOADON AS HD, VATTU AS VT\n" +
//                        "WHERE CT.SoHD = HD.SoHD AND HD.Loai = 'X' AND" +
//                        " CT.MaVT = VT.MaVT AND HD.NgayLap BETWEEN '"+time_one+"' AND '"+timetwo+"'" +
//                        "GROUP BY VT.MaVT, VT.TenVT ORDER BY TONG DESC" ;
//                //System.out.println("SQL = "+sql);
                modelDT.setRowCount(0);
                GiaoDienQuanLy.napModelTop(modelDT,time_one,timetwo);
//                try {
//                    PreparedStatement pre = ConnectSQL.getCon().prepareStatement(sql);
//                    ResultSet rs = pre.executeQuery();
//                    int stt = 1;
//                    while (rs.next()) {
//                        modelDT.addRow(new Object[]{stt, rs.getString("MAVT"), rs.getString("TENVT"),
//                                rs.getInt("SLBAN"), ChuanHoa.ChuyenSoThanhTien(String.valueOf(rs.getInt("TONG")))});
//                        stt ++;
//                    }
//                    pre.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
            }
        }
    }
    public static void setSoLuongVT(int soLuong){
        String slVatTu = String.valueOf(soLuong);
        lbSLVatTu.setText(slVatTu);
    }
    public static void setSoLuongNV(int soLuong){
        String slNhanVien = String.valueOf(soLuong);
        lbSLNhanVien.setText(slNhanVien);
    }
    public static void setSoLuongHD(int SoLuong){
        String slHoaDon = String.valueOf(SoLuong);
        lbSLHoaDon.setText(slHoaDon);
    }
}
