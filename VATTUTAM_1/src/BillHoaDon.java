import DoiTuong.ChiTietHoaDon;
import DoiTuong.HoaDon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BillHoaDon extends JDialog implements ActionListener, KeyListener {
    private final int WIDTH_EDIT = 920;
    private final int HEIGHT_EDIT = 600;
    private JPanel JPmain;
    private JLabel JLBTop;
    private JButton btnTaoHD;
    private JButton btnInHD;
    private JButton btnLuuHDSQL;
    private JLabel JLBCanThanhToan;
    private JLabel JLBDaTra;
    private JTextField txtDaTra;
    private JLabel JLBTienThua;
    private JTextArea JText;
    private JTable tbp;
    private int daTaoHD = -1;
    private String tienThuaConLai;
    private String tongTienHangTB;
    private String nvLapHD;
    private String ngayLapHD;
    private String loaiHD;
    private String soHD;
    private String maNV;
    private static int dangIn;


    public BillHoaDon(JTable tb, String loaiHD, String soHD, String maNV, String tenNV, String ngayLapHD, String tongTien) {
        this.setTitle("CHỈNH SỬA MẶT HÀNG");
        this.setSize(WIDTH_EDIT, HEIGHT_EDIT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setModal(true);
        this.tbp = tb;
        this.tongTienHangTB = tongTien;
        this.nvLapHD = tenNV;
        this.ngayLapHD = ngayLapHD;
        this.loaiHD = loaiHD;
        this.soHD = soHD;
        this.maNV = maNV;

        JPmain = new JPanel();
        JPmain.setBounds(0, 0, WIDTH_EDIT, HEIGHT_EDIT);
        JPmain.setLayout(null);
        JPmain.setBackground(new Color(255, 255, 255));

        JLBTop = new JLabel("HÓA ĐƠN VÀ IN");
        JLBTop.setBackground(new Color(33, 168, 109));
        JLBTop.setOpaque(true);
        JLBTop.setSize(WIDTH_EDIT, 70); // height 70
        JLBTop.setHorizontalAlignment(SwingConstants.CENTER);
        JLBTop.setFont(new Font("Ubuntu", Font.BOLD, 20)); //Helvetica
        JLBTop.setForeground(Color.WHITE);
        JPmain.add(JLBTop);

        ImageIcon money_icon = new ImageIcon(BillHoaDon.class.getResource("Image/money.png"));
        Image image_money = money_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        money_icon.setImage(image_money);
        JLBCanThanhToan = new JLabel("TIỀN HÀNG: " + ChuanHoa.ChuyenSoThanhTien(tongTien) + "đ");
        JLBCanThanhToan.setBounds(30, 140, 270, 40); // height 120
        JLBCanThanhToan.setBackground(new Color(36, 169, 111));
        JLBCanThanhToan.setForeground(Color.WHITE);
        JLBCanThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
        JLBCanThanhToan.setOpaque(true);
        JLBCanThanhToan.setIcon(money_icon);


        JLBDaTra = new JLabel("KHÁCH TRẢ");
        JLBDaTra.setBounds(30, 200, 100, 40); // height 120
        JLBDaTra.setBackground(new Color(36, 169, 111));
        JLBDaTra.setForeground(Color.WHITE);
        JLBDaTra.setHorizontalAlignment(SwingConstants.CENTER);
        JLBDaTra.setOpaque(true);

        txtDaTra = new JTextField();
        txtDaTra.setBounds(140, 200, 160, 40); // 150, 50
        txtDaTra.setBackground(new Color(102, 195, 239));
        txtDaTra.setForeground(new Color(255, 255, 255));
        txtDaTra.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtDaTra.setFont(new Font("Arial", Font.BOLD, 15));

        JLBTienThua = new JLabel("THỪA/THIẾU: " + ChuanHoa.ChuyenSoThanhTien(tongTien) + "đ");
        JLBTienThua.setBounds(30, 260, 270, 40); // height 120
        JLBTienThua.setBackground(new Color(36, 169, 111));
        JLBTienThua.setForeground(Color.WHITE);
        JLBTienThua.setHorizontalAlignment(SwingConstants.CENTER);
        JLBTienThua.setOpaque(true);


        JText = new JTextArea();
        JText.setText("");

        JText.setEditable(false);
        JText.setBackground(new Color(255, 255, 255));
        JText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JText.setBorder(BorderFactory.createEmptyBorder(10, 25, 5, 0));
        JScrollPane JS = new JScrollPane(JText);
        System.out.println(JText.getText());
        JS.setBounds(348, 110, 570, 400); //352

        JPmain.add(JS);

        JPmain.add(JLBCanThanhToan);
        JPmain.add(JLBDaTra);
        JPmain.add(txtDaTra);
        JPmain.add(JLBTienThua);
        txtDaTra.addKeyListener(this);


        btnTaoHD = new JButton("TẠO HD");
        btnTaoHD.setBounds(30, 330, 120, 40); // 220
        btnTaoHD.setBackground(new Color(255, 85, 85));
        btnTaoHD.setBorder(null);
        btnTaoHD.setFocusable(false);
        btnTaoHD.setForeground(Color.WHITE);
        btnTaoHD.addActionListener(this);
        btnTaoHD.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPmain.add(btnTaoHD);

        btnInHD = new JButton("IN HD");
        btnInHD.setBounds(180, 330, 120, 40); // 220
        btnInHD.setBackground(new Color(237, 149, 38));
        btnInHD.setBorder(null);
        btnInHD.setFocusable(false);
        btnInHD.setForeground(Color.WHITE);
        btnInHD.addActionListener(this);
        btnInHD.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPmain.add(btnInHD);

        btnLuuHDSQL = new JButton("LƯU HÓA ĐƠN");
        btnLuuHDSQL.setBounds(105, 420, 120, 40); // 220
        btnLuuHDSQL.setBackground(new Color(154, 92, 150));
        btnLuuHDSQL.setBorder(null);
        btnLuuHDSQL.setFocusable(false);
        btnLuuHDSQL.setForeground(Color.WHITE);
        btnLuuHDSQL.addActionListener(this);
        btnLuuHDSQL.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPmain.add(btnLuuHDSQL);


        this.add(JPmain);
        this.addKeyListener(this);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTaoHD) {
            if (txtDaTra.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập tiền khách trả");
                return;
            }
            setTextHD();
            daTaoHD = 1;
        }
        if (e.getSource() == btnInHD) {
            if (daTaoHD == -1) {
                JOptionPane.showMessageDialog(null, "Bạn cần tạo bản in HD trước khi IN");
                return;
            }
            try {
                JText.print();
                JOptionPane.showMessageDialog(null, "IN Hóa đơn thành công");
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "IN LỖI, vui lòng thử lại sau");
                ex.printStackTrace();
            }
        }

        if (e.getSource() == btnLuuHDSQL) {
            String loai = (loaiHD.equals("Nhập") ? "N":"X");
            java.util.Date day = new java.util.Date();
            HoaDon hoaDon = new HoaDon(soHD,day,loai);
            String truyvan = "INSERT INTO HOADON VALUES(?, ?, ?, ?)";
            try {
                PreparedStatement pre = ConnectSQL.getCon().prepareStatement(truyvan);
                pre.setString(1, soHD);
                pre.setDate(2, new Date(new SimpleDateFormat("yyyy/MM/dd").parse(ngayLapHD).getTime()));
                if(loaiHD.equals("Nhập"))
                    pre.setString(3, "N");
                else pre.setString(3, "X");
                pre.setString(4,maNV);
                pre.executeUpdate();
                pre.close();
            } catch (SQLException | ParseException ex) {
                JOptionPane.showMessageDialog(null, "Thêm Hóa Đơn Lỗi....");
                ex.printStackTrace();
            }

            for (int i = 0; i < tbp.getRowCount(); i++) {
                hoaDon.addChiTiet(new ChiTietHoaDon(soHD,tbp.getValueAt(i,1).toString(),Integer.parseInt(tbp.getValueAt(i,3).toString()),Integer.parseInt(tbp.getValueAt(i, 4).toString()),Float.parseFloat(tbp.getValueAt(i, 5).toString())));
                String sql = "INSERT INTO CTHOADON VALUES (?, ?, ?, ?, ?)";
                try {
                    PreparedStatement pre = ConnectSQL.getCon().prepareStatement(sql);
                    pre.setString(1,soHD);
                    pre.setString(2, tbp.getValueAt(i, 1).toString());
                    pre.setInt(3, Integer.parseInt(tbp.getValueAt(i, 3).toString())); // Long
                    pre.setInt(4, Integer.parseInt(tbp.getValueAt(i, 4).toString()));
                    pre.setFloat(5, Float.parseFloat(tbp.getValueAt(i, 5).toString()));
                    pre.executeUpdate();
                    pre.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Thêm CT Hóa Đơn Lỗi....");
                }
            }
            GiaoDienQuanLy.addHoaDon(maNV,hoaDon);
            JOptionPane.showMessageDialog(null, "Thêm CTHD và Hóa Đơn vào CSDL thành công");

            // 1, "1000", "Xi Măng", 120, 33, 44, "434,353,553"
            String congTru = "";
            if(loaiHD.equals("Nhập")) congTru = "+";
            else congTru = "-";

            for (int i = 0; i<tbp.getRowCount(); i++){
                String sql = "UPDATE VATTU " +
                "SET SoLuongTon = SoLuongTon "+ congTru + " "+tbp.getValueAt(i, 3).toString() +" WHERE MaVT like '"+tbp.getValueAt(i, 1).toString()+"'";
                try {
                    PreparedStatement pre = ConnectSQL.getCon().prepareStatement(sql);
                    pre.executeUpdate();
                    pre.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "LỖI CẬP NHẬT LƯỢNG TỒN VẬT TƯ");
                }
            }
            GiaoDienQuanLy.updateVatTu(congTru,tbp);
            JOptionPane.showMessageDialog(null, "CẬP NHẬT LƯỢNG TỒN VẬT TƯ THÀNH CÔNG");
            //System.out.println("UPDATE = "+sql);

            JPanelHoaDon.reSetALL();
            JPanelVatTu.reSetAll();
            JPanelNhanVien.reSetAll();
            JPanelVatTu.setDanglapHD_VT(false);
            this.dispose();
            }
        }

        @Override
        public void keyTyped (KeyEvent e){

        }

        @Override
        public void keyPressed (KeyEvent e){
            if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == 8 && txtDaTra.getText().length() != 0) {
                txtDaTra.setEditable(true);
            } else {
                txtDaTra.setEditable(false);
            }
        }

        @Override
        public void keyReleased (KeyEvent e){
            if (e.getSource() == txtDaTra) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == 8 && txtDaTra.getText().length() != 0) {
                    txtDaTra.setEditable(true);
                    long tienthua = Long.parseLong(txtDaTra.getText()) - Long.parseLong(tongTienHangTB);
                    if (tienthua < 0) {
                        JLBTienThua.setText("THỪA/THIẾU: -" + ChuanHoa.ChuyenSoThanhTien(String.valueOf(tienthua * -1)));
                        tienThuaConLai = "-" + ChuanHoa.ChuyenSoThanhTien(String.valueOf(tienthua * -1));
                    } else {
                        JLBTienThua.setText("THỪA/THIẾU: " + ChuanHoa.ChuyenSoThanhTien(String.valueOf(tienthua)));
                        tienThuaConLai = ChuanHoa.ChuyenSoThanhTien(String.valueOf(tienthua));
                    }
                } else {
                    txtDaTra.setEditable(false);
                }
            }
        }

        public void setTextHD () {
            JText.setText("              C.TY CUNG CẤP VẬT TƯ SỐ 1 THẾ GIỚI\n");
            JText.setText(JText.getText() + "               ĐỊA CHỈ: X8. D.HOÀNG, D.CHÂU, N.AN\n");
            JText.setText(JText.getText() + "                       SĐT: 0378544081\n");
            JText.setText(JText.getText() + "                       PHIẾU TÍNH TIỀN\n");
            JText.setText(JText.getText() + "                      *****************          \n\n");
            JText.setText(JText.getText() + "Số Hóa Đơn: " + soHD + "\n");
            JText.setText(JText.getText() + "Loại Hóa Đơn: " + loaiHD + "\n");
            JText.setText(JText.getText() + "Tiền Thuế: " + "Đã Bao Gồm VAT" + "\n");
            JText.setText(JText.getText() + "\n" + String.format("%-5s%-12s%-16s%-12s%-5s%-12s", "STT", "SO LUONG", "TEN MAT HANG", "DON GIA", "VAT", "THANH TIEN"));
            for (int i = 0; i < tbp.getRowCount(); i++) {
                String s = "";
                if (i + 1 < 10) {
                    s = "0" + (i + 1);
                } else s = String.valueOf(i + 1);
                JText.setText(JText.getText() + "\n" + String.format("%-5s%-12s%-16s%-12s%-5s%-12s",
                        s, tbp.getValueAt(i, 3).toString(),
                        tbp.getValueAt(i, 2).toString(), tbp.getValueAt(i, 4).toString(),
                        tbp.getValueAt(i, 5).toString(), tbp.getValueAt(i, 6).toString()));
            }
            JText.setText(JText.getText() + "\n" + "*************************************************************\n");
            JText.setText(JText.getText() + String.format("%-50s%-12s", "TỔNG CỘNG:", ChuanHoa.ChuyenSoThanhTien(tongTienHangTB)) + "\n");
            JText.setText(JText.getText() + String.format("%-50s%-12s", "TIỀN MẶT :", ChuanHoa.ChuyenSoThanhTien(txtDaTra.getText())) + "\n");
            JText.setText(JText.getText() + String.format("%-50s%-12s", "CÒN LẠI  :", tienThuaConLai) + "\n");
            JText.setText(JText.getText() + "*************************************************************\n");
            JText.setText(JText.getText() + String.format("%-30s%-20s", "THU NGÂN :", nvLapHD) + "\n");
            SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
            JText.setText(JText.getText() + String.format("%-30s%-20s", "NGÀY LẬP :", ngayLapHD) + "\n\n");
            JText.setText(JText.getText() + "                       CẢM ƠN QUÝ KHÁCH\n");
            JText.setText(JText.getText() + "                         HẸN GẶP LẠI\n");
            JText.setText(JText.getText() + "                  Website: www.kiemthecao.com\n");
        }

    }

//    class TEST_BILL {
//        public static void main(String[] args) {
//            new ConnectSQL();
//
//            String[] columnJT1 = {"STT", "MÃ VẬT TƯ", "TÊN VẬT TƯ", "SỐ LƯỢNG", "ĐƠN GIÁ", "VAT", "THÀNH TIỀN"};
//            Object[][] dataJT1 = {{1, "1000", "Xi Măng", 120, 33, 44, "434,353,553"},
////                    {1, "77", "Xi Măng", 120, 33, 44, "353,553"},
////                    {1, "77", "Xi Măng", 120, 33, 44, "353,553"},
////                    {1, "77", "Xi Măng", 120, 33, 44, "353,553"},
////                    {1, "77", "Xi Măng", 120, 33, 44, "353,553"},
////                    {1, "77", "Xi Măng", 120, 33, 44, "353,553"},
////                    {1, "77", "Xi Măng", 120, 33, 44, "353,553"},
////                    {1, "77", "Xi Măng", 120, 33, 44, "353,553"},
//            };
//            DefaultTableModel model = new DefaultTableModel(dataJT1, columnJT1);
//            JTable tb = new JTable(model);
//            new BillHoaDon(tb, "Nhập", "253663664", "2002", "HỒ KHÁNH AN TOAN",
//                    "20/12/2021", "25662662");
//
//            try {
//                String s = "20/12/2021";
//                System.out.println(new SimpleDateFormat("yyyy/MM/dd").parse(s));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//    }

