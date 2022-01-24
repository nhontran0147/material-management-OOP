import DoiTuong.NhanVien;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JPanelHoaDon extends JPanel implements KeyListener, MouseListener {
    private static DefaultTableModel modelVatTu;
    private static DefaultTableModel modelHoaDon;
    private static DefaultTableModel modelNhanVien;
    private JTable tbvattu;
    private JTable tbnhanvien;
    private static JTable tbhoadon;
    private static String vatTuValueSelected;
    private static String vatTuNameSelected;
    private static String nhanvienNameSelected;
    private static String hoaDonValueSelected;
    private static String nhanVienValueSelected;
    private static String conlaiVatTuValueSelected;
    private static String conlaiMatHangValueSelected;
    private static String maMatHang;
    private static String tenMatHang;
    private static String donGiaMatHang;
    private static String soLuongMatHang;
    private static String vatMatHang;
    private static JLabel lbtongtien;


    private int vatTuRowSelected = -1;
    private static int hoaDonRowSelected = -1;
    private int nhanVienRowSelected = -1;
    private JLabel lbloaihd;
    private JLabel lbngaylap;
    private JLabel lbValueNgayLap;
    private JLabel lbsohd;
    private JLabel lbsotaohd;
    private JLabel lbsoluong;
    private JLabel lbvat;
    private JLabel lbdongia;
    private static String txtloaihd = "";
    private JLabel lbhdnhap;
    private JLabel lbhdxuat;
   // private static JDateChooser datelaphd;
    private static JTextField txtsohd;
    private static JTextField txtsoluong;
    private static JTextField txtvat;
    private static JTextField txtdongia;
    private JLabel lbtimnv;
    private JLabel lbtimvt;
    private JTextField txttimvt;
    private JTextField txttimnv;
    private static JLabel mavtchoosed;
    private static JLabel manvchoosed;
    private static JLabel lbthongbao;

    private JLabel btnaddHD;
    private JLabel btneditHD;
    private JLabel btnremoveHD;
    private JLabel btnresetHD;
    private JLabel btnsaveHD;


    public JPanelHoaDon() {
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);

        String[] columnJT = {"MAVT", "TENVT", "CON LAI", "DON VI TINH"};
        Object[][] dataJT = {};
        modelVatTu = new DefaultTableModel(dataJT, columnJT);
        tbvattu = new JTable(modelVatTu);
        GiaoDienQuanLy.setModelVatTu(modelVatTu,false);

        tbvattu.setFillsViewportHeight(true);
        tbvattu.setOpaque(true);
        tbvattu.setShowGrid(false); // Tắt kẻ bảng
        tbvattu.setShowHorizontalLines(false); // Tắt kẻ ngang
        tbvattu.setShowVerticalLines(false); // Tắt kẻ dọc
        tbvattu.setFont(new Font("SansSerif", Font.PLAIN, 15));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbvattu.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Chữ ra giữa bảng
        tbvattu.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbvattu.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbvattu.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbvattu.setRowHeight(30);
        tbvattu.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16)); // Set font Header
        tbvattu.getTableHeader().setPreferredSize(new Dimension(100, 40)); // Set height
        tbvattu.setSelectionBackground(Color.YELLOW);

        tbvattu.setBackground(new Color(255, 255, 255));
        tbvattu.setForeground(new Color(84, 101, 121));
        tbvattu.getTableHeader().setBackground(new Color(247, 249, 252));
        tbvattu.getTableHeader().setForeground(new Color(105, 121, 141));

        tbvattu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JScrollPane JS = new JScrollPane(tbvattu);
        JS.setBounds(497, 150, 450, 140); // w: 700 bg: 100


        tbvattu.setFocusable(false); // Không tạo focus 1 phần tử
        tbvattu.setDefaultEditor(Object.class, null); // Không cho edit table

        // Get row and value click
        tbvattu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (tbvattu.getSelectedRow() > -1) {
                    vatTuValueSelected = tbvattu.getValueAt(tbvattu.getSelectedRow(), 0).toString();
                    vatTuNameSelected = tbvattu.getValueAt(tbvattu.getSelectedRow(), 1).toString();
                    conlaiVatTuValueSelected = tbvattu.getValueAt(tbvattu.getSelectedRow(), 2).toString();

                    //System.out.println("Xóa mã vật tư " + vatTuValueSelected + ", row: " + tbvattu.getSelectedRow() +
                      //      "SL con lai = "+ conlaiVatTuValueSelected + " bảng vật tư");
                    mavtchoosed.setText(vatTuValueSelected);
                }
            }
        })  ;


        String[] columnJT1 = {"MANV", "HONV", "TENNV", "PHAI"};
        String[][] dataJT1 = {};

        modelNhanVien = new DefaultTableModel(dataJT1, columnJT1);
        tbnhanvien = new JTable(modelNhanVien);
        NhanVien nhanVien = GiaoDienQuanLy.getThongTinNhanVienlap();

        nhanvienNameSelected=nhanVien.getHoNhanVien()+" "+nhanVien.getTenNhanVien();
        modelNhanVien.addRow(nhanVien.toObjectsSTT());
        nhanVienValueSelected=nhanVien.getMaNhanVien();



        tbnhanvien.setFillsViewportHeight(true);
        tbnhanvien.setOpaque(true);
        tbnhanvien.setShowGrid(false); // Tắt kẻ bảng
        tbnhanvien.setShowHorizontalLines(false); // Tắt kẻ ngang
        tbnhanvien.setShowVerticalLines(false); // Tắt kẻ dọc
        tbnhanvien.setFont(new Font("SansSerif", Font.PLAIN, 15));
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbnhanvien.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Chữ ra giữa bảng
        tbnhanvien.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbnhanvien.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbnhanvien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbnhanvien.setRowHeight(30);
        tbnhanvien.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16)); // Set font Header
        tbnhanvien.getTableHeader().setPreferredSize(new Dimension(100, 40)); // Set height

        tbnhanvien.setBackground(new Color(255, 255, 255));
        tbnhanvien.setForeground(new Color(84, 101, 121));
        tbnhanvien.getTableHeader().setBackground(new Color(247, 249, 252));
        tbnhanvien.getTableHeader().setForeground(new Color(105, 121, 141));

        tbnhanvien.setSelectionBackground(Color.YELLOW);
        tbnhanvien.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        JScrollPane JS1 = new JScrollPane(tbnhanvien);
        JS1.setBounds(0, 150, 400, 140); // w: 700 bg: 100
        tbnhanvien.setFocusable(false); // Không tạo focus 1 phần tử
        tbnhanvien.setDefaultEditor(Object.class, null); // Không cho edit table
        // Get row and value click
//        tbnhanvien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent event) {
//                if (tbnhanvien.getSelectedRow() > -1) {
//                    nhanVienValueSelected = tbnhanvien.getValueAt(tbnhanvien.getSelectedRow(), 0).toString();
//                    nhanVienRowSelected = tbnhanvien.getSelectedRow();
//                    nhanvienNameSelected = tbnhanvien.getValueAt(tbnhanvien.getSelectedRow(), 1).toString()+" "+
//                            tbnhanvien.getValueAt(tbnhanvien.getSelectedRow(), 2);
//                    System.out.println("Xóa mã nhân viên: " + nhanVienValueSelected + ", row: " + nhanVienRowSelected + " bảng nhân viên");
//                    manvchoosed.setText(nhanVienValueSelected);
//                }
//            }
//        });

        JS1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);


        lbloaihd = new JLabel("Loại Hóa Đơn:");
        lbloaihd.setBounds(30, 50, 100, 30);



        lbhdnhap = new JLabel("NHẬP");
        lbhdnhap.setBounds(150, 50, 70, 30);
        lbhdnhap.setBackground(new Color(102, 195, 239));
        lbhdnhap.setOpaque(true);
        lbhdnhap.setForeground(new Color(255, 255, 255));
        lbhdnhap.setHorizontalAlignment(SwingConstants.CENTER);

        lbhdxuat = new JLabel("XUÂT");
        lbhdxuat.setBounds(230, 50, 70, 30);
        lbhdxuat.setBackground(new Color(102, 195, 239));
        lbhdxuat.setOpaque(true);
        lbhdxuat.setForeground(new Color(255, 255, 255));
        lbhdxuat.setHorizontalAlignment(SwingConstants.CENTER);

        lbngaylap = new JLabel("Ngày Lập: ");
        lbngaylap.setBounds(350, 50, 60, 30);


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat fm1 = new SimpleDateFormat("dd/MM/yyyy");
        String da= fm1.format(cal.getTime());
        lbValueNgayLap = new JLabel(da);
        lbValueNgayLap.setOpaque(true);
       // lbValueNgayLap.setBackground(new Color(102 ,195,239));
        lbValueNgayLap.setBounds(450,50,120,30);
        lbValueNgayLap.setForeground(new Color(33, 205, 65));
        lbValueNgayLap.setHorizontalAlignment(SwingConstants.CENTER);
        lbValueNgayLap.setFont(new Font("Arial", Font.PLAIN, 17));
        //datelaphd = new JDateChooser(cal.getTime());
        //datelaphd.setEnabled();
//        datelaphd.setBounds(450, 50, 120, 30);
//        datelaphd.setFont(new Font("Arial", Font.PLAIN, 15));
//        datelaphd.setDateFormatString("dd/MM/yyyy");

        lbsohd = new JLabel("Số Hóa Đơn: ");
        lbsohd.setBounds(600, 50, 80, 30);
        txtsohd = new JTextField();
        txtsohd.setBounds(710, 50, 150, 30);
        txtsohd.setBackground(new Color(102, 195, 239));
        txtsohd.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding
        txtsohd.setFont(new Font("Arial", Font.PLAIN, 15));
        txtsohd.setForeground(new Color(255,255,255));
        txtsohd.setEditable(false);
        txtsohd.setText("");

        lbsotaohd = new JLabel("TẠO");
        lbsotaohd.setBounds(870, 50, 50, 30);
        lbsotaohd.setBackground(new Color(255, 85, 85));
        lbsotaohd.setOpaque(true);
        lbsotaohd.setHorizontalAlignment(SwingConstants.CENTER);
        lbsotaohd.setForeground(new Color(255,255,255));
        this.add(lbsotaohd);

        lbtimnv = new JLabel("Nhân viên lập: ");
        lbtimnv.setBounds(30, 100, 100, 30);
        txttimnv = new JTextField();
        txttimnv.setBounds(150, 100, 150, 30);
        txttimnv.setBackground(new Color(102, 195, 239));
        txttimnv.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding
        txttimnv.setText(nhanVien.getHoNhanVien()+ " " + nhanVien.getTenNhanVien());
        lbtimvt = new JLabel("Tìm Vật Tư: ");
        lbtimvt.setBounds(600, 100, 100, 30);
        txttimvt = new JTextField();
        txttimvt.setBounds(710, 100, 150, 30);
        txttimvt.setBackground(new Color(102, 195, 239));
        txttimvt.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding


        manvchoosed = new JLabel();
        manvchoosed.setBounds(310, 100, 50, 30);
        manvchoosed.setText("NULL");
        manvchoosed.setBackground(new Color(42, 185, 48));
        manvchoosed.setOpaque(true);
        manvchoosed.setHorizontalAlignment(SwingConstants.CENTER);
        manvchoosed.setForeground(new Color(255,255,255));
        manvchoosed.setText(nhanVienValueSelected);
        this.add(manvchoosed);

        mavtchoosed = new JLabel();
        mavtchoosed.setBounds(870, 100, 50, 30);
        mavtchoosed.setText("NULL");
        mavtchoosed.setBackground(new Color(42, 185, 48));
        mavtchoosed.setOpaque(true);
        mavtchoosed.setHorizontalAlignment(SwingConstants.CENTER);
        mavtchoosed.setForeground(new Color(255,255,255));
        this.add(mavtchoosed);


        lbsoluong = new JLabel("Số Lượng: ");
        lbsoluong.setBounds(30, 310, 70, 30);
        txtsoluong = new JTextField();
        txtsoluong.setBounds(150, 310, 150, 30);
        txtsoluong.setBackground(new Color(102, 195, 239));
        txtsoluong.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding

        lbdongia = new JLabel("Đơn Giá: ");
        lbdongia.setBounds(350, 310, 60, 30);
        txtdongia = new JTextField();
        txtdongia.setBounds(430, 310, 150, 30);
        txtdongia.setBackground(new Color(102, 195, 239));
        txtdongia.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding

        lbvat = new JLabel("VAT: ");
        lbvat.setBounds(670, 310, 50, 30);
        txtvat = new JTextField();
        txtvat.setBounds(710, 310, 150, 30);
        txtvat.setBackground(new Color(102, 195, 239));
        txtvat.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding


        lbthongbao = new JLabel("Bạn chưa chọn mặt hàng nào");
        lbthongbao.setBounds(0, 660, 950, 40);
        lbthongbao.setFont(new Font("Ubuntu", Font.PLAIN, 15));
        lbthongbao.setHorizontalAlignment(SwingConstants.CENTER);

        lbtongtien = new JLabel("Tổng "+ChuanHoa.ChuyenSoThanhTien("0")+"đ");
        lbtongtien.setBounds(700, 660, 200, 40);
        lbtongtien.setForeground(new Color(255, 255, 255));
        lbtongtien.setBackground(new Color(42, 185, 48));
        lbtongtien.setOpaque(true);
        lbtongtien.setFont(new Font("Ubuntu", Font.PLAIN, 15));
        lbtongtien.setHorizontalAlignment(SwingConstants.CENTER);


        btnaddHD = new JLabel("THÊM MẶT HÀNG");
        btnaddHD.setBounds(100, 370, 160, 40); // 150
        btnaddHD.setBackground(new Color(22, 114, 236));
        btnaddHD.setOpaque(true);
        btnaddHD.setForeground(Color.WHITE);
        btnaddHD.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon add_icon = new ImageIcon(JPanelVatTu.class.getResource("add.png"));
        Image image_add = add_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        add_icon.setImage(image_add);
        btnaddHD.setIcon(add_icon);
        btnaddHD.setIconTextGap(10);

        btneditHD = new JLabel("CHỈNH SỬA");
        btneditHD.setBounds(270, 370, 140, 40);
        btneditHD.setBackground(new Color(42, 185, 48));
        btneditHD.setOpaque(true);
        btneditHD.setForeground(Color.WHITE);
        btneditHD.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon edit_icon = new ImageIcon(JPanelVatTu.class.getResource("edit.png"));
        Image image_edit = edit_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        edit_icon.setImage(image_edit);
        btneditHD.setIcon(edit_icon);
        btneditHD.setIconTextGap(10);


        btnremoveHD = new JLabel("XÓA MẶT HÀNG");
        btnremoveHD.setBounds(420, 370, 140, 40);
        btnremoveHD.setBackground(new Color(237, 149, 38));
        btnremoveHD.setOpaque(true);
        btnremoveHD.setForeground(Color.WHITE);
        btnremoveHD.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon remove_icon = new ImageIcon(JPanelVatTu.class.getResource("remove.png"));
        Image image_remove = remove_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        remove_icon.setImage(image_remove);
        btnremoveHD.setIcon(remove_icon);
        btnremoveHD.setIconTextGap(10);

        btnresetHD = new JLabel("RESET INPUT");
        btnresetHD.setBounds(570, 370, 140, 40);
        btnresetHD.setBackground(new Color(154, 15, 191));
        btnresetHD.setOpaque(true);
        btnresetHD.setForeground(Color.WHITE);
        btnresetHD.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon reset_icon = new ImageIcon(JPanelVatTu.class.getResource("reset.png"));
        Image image_reset = reset_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        reset_icon.setImage(image_reset);
        btnresetHD.setIcon(reset_icon);
        btnresetHD.setIconTextGap(10);

        btnsaveHD = new JLabel("LƯU VÀ IN");
        btnsaveHD.setBounds(720, 370, 140, 40);
        btnsaveHD.setBackground(new Color(255, 85, 85));
        btnsaveHD.setOpaque(true);
        btnsaveHD.setForeground(Color.WHITE);
        btnsaveHD.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon save_icon = new ImageIcon(JPanelVatTu.class.getResource("reset.png"));
        Image image_save = save_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        save_icon.setImage(image_save);
        btnsaveHD.setIcon(save_icon);
        btnsaveHD.setIconTextGap(10);


        this.add(lbloaihd);
        this.add(lbngaylap);
        this.add(lbsohd);
        this.add(lbtimnv);
        this.add(lbtimvt);
        this.add(lbsoluong);
        this.add(lbdongia);
        this.add(lbvat);
        this.add(btnaddHD);
        this.add(btneditHD);
        this.add(btnremoveHD);
        this.add(btnresetHD);
        this.add(btnsaveHD);
        this.add(lbthongbao);
        this.add(lbtongtien);

        this.add(lbhdnhap);
        this.add(lbhdxuat);

//        this.add(txtngaylap);
        this.add(lbValueNgayLap);
        //this.add(datelaphd);
        txtsoluong.addKeyListener(this);
        txtdongia.addKeyListener(this);
        txtvat.addKeyListener(this);
        this.add(txtsohd);
        this.add(txttimnv);
        this.add(txttimvt);
        this.add(txtsoluong);
        this.add(txtdongia);
        this.add(txtvat);

        btnresetHD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnremoveHD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btneditHD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnaddHD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnsaveHD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        txttimnv.setEditable(false);
        txttimvt.addKeyListener(this);
        txttimnv.addKeyListener(this);
        lbhdxuat.addMouseListener(this);
        lbhdnhap.addMouseListener(this);
        btnaddHD.addMouseListener(this);
        btneditHD.addMouseListener(this);
        btnremoveHD.addMouseListener(this);
        btnresetHD.addMouseListener(this);
        btnsaveHD.addMouseListener(this);
        lbsotaohd.addMouseListener(this);


        String[] columnJT2 = {"STT", "MAVT", "TENVT", "SOLUONG", "DONGIA", "VAT", "THANHTIEN"};
        Object[][] dataJT2 = {};


        modelHoaDon = new DefaultTableModel(dataJT2, columnJT2);
        tbhoadon = new JTable(modelHoaDon);
        tbhoadon.setFillsViewportHeight(true);
        tbhoadon.setBackground(new Color(248, 249, 250));
        tbhoadon.setOpaque(true);
        tbhoadon.setShowGrid(false); // Tắt kẻ bảng
        tbhoadon.setShowHorizontalLines(false); // Tắt kẻ ngang
        tbhoadon.setShowVerticalLines(false); // Tắt kẻ dọc
        tbhoadon.setForeground(new Color(0, 123, 255));
        tbhoadon.setFont(new Font("SansSerif", Font.PLAIN, 15));
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbhoadon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Chữ ra giữa bảng
        tbhoadon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbhoadon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbhoadon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbhoadon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbhoadon.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbhoadon.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbhoadon.setRowHeight(30);
        tbhoadon.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16)); // Set font Header
        tbhoadon.getTableHeader().setPreferredSize(new Dimension(100, 40)); // Set height
        tbhoadon.getTableHeader().setBackground(new Color(121, 195, 70));
        tbhoadon.getTableHeader().setForeground(Color.WHITE);
        tbhoadon.setSelectionBackground(Color.YELLOW);
        tbhoadon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tbhoadon.getColumnModel().getColumn(0).setMaxWidth(70);
        tbhoadon.getColumnModel().getColumn(0).setPreferredWidth(70);


        JScrollPane JS2 = new JScrollPane(tbhoadon);
        JS2.setBounds(0, 430, 930, 230); // w: 700 bg: 100
        tbhoadon.setFocusable(false); // Không tạo focus 1 phần tử
        tbhoadon.setDefaultEditor(Object.class, null); // Không cho edit table

        // Get row and value click
        tbhoadon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (tbhoadon.getSelectedRow() > -1) {
                    hoaDonValueSelected = tbhoadon.getValueAt(tbhoadon.getSelectedRow(), 1).toString();
                    hoaDonRowSelected = tbhoadon.getSelectedRow();
                    maMatHang = tbhoadon.getValueAt(tbhoadon.getSelectedRow(), 1).toString();
                    tenMatHang = tbhoadon.getValueAt(tbhoadon.getSelectedRow(), 2).toString();
                    soLuongMatHang = tbhoadon.getValueAt(tbhoadon.getSelectedRow(), 3).toString();
                    donGiaMatHang = tbhoadon.getValueAt(tbhoadon.getSelectedRow(), 4).toString();
                    vatMatHang = tbhoadon.getValueAt(tbhoadon.getSelectedRow(), 5).toString();
                    lbthongbao.setText("Bạn đang chọn mặt hàng "+maMatHang+" _ "+tenMatHang);
                    System.out.println("Xóa mã hóa đơn: " + maMatHang + " + "+tenMatHang+ ", row: " + hoaDonRowSelected + " bảng hóa đơn");
                }
            }
        });
        txtloaihd="";

        this.add(JS);
        this.add(JS1);
        this.add(JS2);
    }

//    private void add(String txtloaihd) {
//    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource()==txtsoluong){
            if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && txtsoluong.getText().length()<6) || e.getKeyCode() == 8 || e.getKeyCode()==127) {
                txtsoluong.setEditable(true);
            } else {
                txtsoluong.setEditable(false);
            }
        }
        if (e.getSource()==txtdongia){
            if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && txtdongia.getText().length()<9) || e.getKeyCode() == 8 || e.getKeyCode()==127) {
                txtdongia.setEditable(true);
            } else {
                txtdongia.setEditable(false);
            }
        }
        if (e.getSource()==txtvat){
            if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && txtvat.getText().length()<2) || e.getKeyCode() == 8 || e.getKeyCode()==127) {
                txtvat.setEditable(true);
            } else {
                txtvat.setEditable(false);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == txttimvt) {
            vatTuValueSelected = null;
            vatTuRowSelected = -1;
            mavtchoosed.setText("NULL");
            modelVatTu.setRowCount(0);
            GiaoDienQuanLy.napModelTimKiem(modelVatTu,txttimvt.getText());
     }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == lbhdnhap || e.getSource() == lbhdxuat){
            if(tbhoadon.getRowCount() > 0){
                JOptionPane.showMessageDialog(null, "Bạn đang lập HD "+txtloaihd+". Vui lòng xóa" +
                        " hết các mặt hàng trước khi chuyển");
                return;
            }
        }
        if(e.getSource() == lbhdnhap){
            lbhdnhap.setBackground(new Color(42, 185, 48));
            lbhdxuat.setBackground(new Color(102, 195, 239));
            txtloaihd = "Nhập";
        }
        if(e.getSource() == lbhdxuat){
            lbhdxuat.setBackground(new Color(42, 185, 48));
            lbhdnhap.setBackground(new Color(102, 195, 239));
            txtloaihd = "Xuất";
        }

        if(e.getSource() == lbsotaohd){
            if(tbhoadon.getRowCount()>0){
                JOptionPane.showMessageDialog(null,"Bạn đang lập hóa đơn, không thể tạo số hóa đơn mới!");
                return;
            }
            SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
            String birthday = "31/12/2021";
            try {
                Date One = fm.parse(birthday);
                Date Two = Calendar.getInstance().getTime();
                long kc = (Two.getTime() - One.getTime());
                txtsohd.setText(String.valueOf(kc));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == btnaddHD){
            if(txtloaihd.equals("")){
                JOptionPane.showMessageDialog(null, "Bạn chưa loại hóa đơn!");
                return;
            }
            if(txtsohd.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Bạn chưa tạo số hóa đơn");
                return;
            }
            if(vatTuNameSelected == null
                    || vatTuValueSelected == null
                    || mavtchoosed.getText() == "NULL"
            ){
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn vật tư");
                return;
            }

            ///can fix lai
            if(nhanVienValueSelected == null || manvchoosed.equals("") || manvchoosed.getText() == "NULL"){
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên");
                return;
            }
            ////////

            if(txtsoluong.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng");
                return;
            }
            if(txtdongia.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đơn giá");
                return;
            }
            if(txtvat.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập phí VAT");
                return;
            }
            if (Integer.parseInt(txtdongia.getText())==0 || Integer.parseInt(txtsoluong.getText())==0){
                JOptionPane.showMessageDialog(null,"Số lượng và đơn giá không được bằng 0 !");
                return;
            }
            if(txtloaihd.equals("Xuất") && conlaiVatTuValueSelected != null && Integer.parseInt(conlaiVatTuValueSelected) < Integer.parseInt(txtsoluong.getText())){
               // System.out.println("Con Lai Selected = "+ conlaiVatTuValueSelected);
                JOptionPane.showMessageDialog(null, "Số lượng Xuất vượt quá lượng tồn!. \n" +
                        vatTuValueSelected+" _ "+vatTuNameSelected+" Còn lại: "+ conlaiVatTuValueSelected);
                return;
            }
            if(CheckSanPhamDaTonTai() == true){
                CapNhatMatHang();
                return;
            }
            modelHoaDon.addRow(new Object[]{tbhoadon.getRowCount() + 1, vatTuValueSelected, vatTuNameSelected, txtsoluong.getText(), txtdongia.getText(),
                    txtvat.getText(), ChuanHoa.ChuyenSoThanhTien(String.valueOf((long)(
                    Double.parseDouble(txtdongia.getText()) * Double.parseDouble(txtsoluong.getText()) * (Integer.parseInt(txtvat.getText())/100.0 + 1))))});
            tinhTongChiPhi();
            JPanelVatTu.setDanglapHD_VT(true);
            //JPanelNhanVien.setDanglapHD_NV(true);
        }

        if(e.getSource() == btneditHD){
            if(hoaDonRowSelected == -1){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một mặt hàng để chỉnh sửa");
                return;
            }
            conlaiMatHangValueSelected = getSoLuongTonMatHangSelect();
            System.out.println("HUHU");
            if(conlaiMatHangValueSelected != null){
                if(EditHoaDon.getDangEdit() == 1){
                    JOptionPane.showMessageDialog(null, "Bạn không thể chỉnh sửa 2 mặt hàng cùng lúc");
                    return;
                }
                new EditHoaDon(maMatHang, tenMatHang, conlaiMatHangValueSelected, txtloaihd, soLuongMatHang, donGiaMatHang, vatMatHang);
            }

        }

        if(e.getSource() == btnremoveHD){
            if(hoaDonRowSelected == -1){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một mặt hàng để hóa");
                return;
            }
            int choose = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa mặt hàng "+maMatHang+"_"+tenMatHang);
            if(choose == 0){
                modelHoaDon.removeRow(hoaDonRowSelected);
                loadSTT();
                JOptionPane.showMessageDialog(null, "Xóa mặt hàng thành công");
                if (modelHoaDon.getRowCount()==0){
                    JPanelVatTu.setDanglapHD_VT(false);
                }
                hoaDonRowSelected = -1;
                lbthongbao.setText("Bạn chưa chọn mặt hàng nào");
            }

        }


        if(e.getSource() == btnresetHD){
            txtvat.setText("");
            txtdongia.setText("");
            txtsoluong.setText("");
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu nhập thành công");
        }

        if(e.getSource() == btnsaveHD){
            if(tbhoadon.getRowCount() < 1){
                JOptionPane.showMessageDialog(null, "Chưa có mặt hàng nào");
                return;
            }
            //if(CheckDate() == false) return; // Check Date
            int choose = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn lưu và in Hóa Đơn");
            if(choose == 0){
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat fm1 = new SimpleDateFormat("yyyy/MM/dd");
                String da= fm1.format(cal.getTime());
                new BillHoaDon(tbhoadon, txtloaihd, txtsohd.getText(), nhanVienValueSelected, nhanvienNameSelected, da, String.valueOf(ChuanHoa.ChuyenTienSangSo(lbtongtien.getText())));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == btnaddHD) {
            btnaddHD.setBackground(new Color(255, 209, 30));
        }
        if (e.getSource() == btneditHD) {
            btneditHD.setBackground(new Color(255, 209, 30));
        }
        if (e.getSource() == btnremoveHD) {
            btnremoveHD.setBackground(new Color(255, 209, 30));
        }
        if (e.getSource() == btnresetHD) {
            btnresetHD.setBackground(new Color(255, 209, 30));
        }
        if (e.getSource() == btnsaveHD) {
            btnsaveHD.setBackground(new Color(255, 209, 30));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == btnaddHD) {
            btnaddHD.setBackground(new Color(22, 114, 236));
        }
        if (e.getSource() == btneditHD) {
            btneditHD.setBackground(new Color(42, 185, 48));
        }
        if (e.getSource() == btnremoveHD) {
            btnremoveHD.setBackground(new Color(237, 149, 38));
        }
        if (e.getSource() == btnresetHD) {
            btnresetHD.setBackground(new Color(154, 15, 191));
        }
        if (e.getSource() == btnsaveHD) {
            btnsaveHD.setBackground(new Color(255, 85, 85));
        }
    }

    public static void upDateListVatTu(){
        modelVatTu.setRowCount(0);
        GiaoDienQuanLy.setModelVatTu(modelVatTu,false);
        conlaiVatTuValueSelected = null;
    }


    public static void setVatTuValueSelected(String vatTuValueSelected) {
        JPanelHoaDon.vatTuValueSelected = vatTuValueSelected;
    }

    public static void setVatTuNameSelected(String vatTuNameSelected) {
        JPanelHoaDon.vatTuNameSelected = vatTuNameSelected;
    }

    public static void setNhanVienValueSelected(String nhanVienValueSelected) {
        JPanelHoaDon.nhanVienValueSelected = nhanVienValueSelected;
    }

    public static void setMavtchoosed(String tieude) {
        JPanelHoaDon.mavtchoosed.setText(tieude);
    }
//    public static void setManvchoosed(String tieude) {
//        JPanelHoaDon.manvchoosed.setText(tieude);
//    }

    public boolean CheckSanPhamDaTonTai(){
        for (int i = 0; i < tbhoadon.getRowCount(); i++)
            if (tbhoadon.getValueAt(i, 1).equals(mavtchoosed.getText())) return true;
        return false;
    }
    public void CapNhatMatHang(){
        int choose = JOptionPane.showConfirmDialog(null, "Mã vật tư đã tồn tại trong hóa đơn, bạn có muốn cập nhật lại?");
        if(choose == 0){
            for (int i = 0; i < tbhoadon.getRowCount(); i++) {
                if (tbhoadon.getValueAt(i, 1).toString().equals(mavtchoosed.getText())){
                    tbhoadon.setValueAt(txtsoluong.getText(), i, 3);
                    tbhoadon.setValueAt(txtdongia.getText(), i, 4);
                    tbhoadon.setValueAt(txtvat.getText(), i, 5);
                    tbhoadon.setValueAt(ChuanHoa.ChuyenSoThanhTien(String.valueOf(
                            (Long.parseLong(txtdongia.getText())) * (((long) Long.parseLong(txtsoluong.getText()) * (100 + Integer.parseInt(txtvat.getText())))) / 100)), i, 6);

                    // Cap nhat bien matHang
//                    donGiaMatHang = txtdongia.getText();
//                    vatMatHang = txtvat.getText();
//                    soLuongMatHang = txtsoluong.getText();
                    JOptionPane.showMessageDialog(null, "Cập nhật mặt hàng thành công");
                    break;
                };
            }
        }
        tinhTongChiPhi();
    }

    public void loadSTT(){
        int stt = 1;
        for (int i = 0; i < tbhoadon.getRowCount(); i++) {
            tbhoadon.setValueAt(stt, i, 0);
            stt ++;
        }
        tinhTongChiPhi();
    }

    public String getSoLuongTonMatHangSelect() {
        System.out.println("maMH selected = "+maMatHang);
        for (int i = 0; i < tbvattu.getRowCount(); i++)
            if (tbvattu.getValueAt(i, 0).toString().equals(maMatHang)) {
                System.out.println("Lấy được số lượng tồn của MH select = " + tbvattu.getValueAt(i, 2).toString());
                return tbvattu.getValueAt(i, 2).toString();
            }
        System.out.println("NULL rồi");
        return null;
    }

    public static void upDateMatHangEdit(String MaVT, String SoLuong, String DonGia, String Vat){
        for(int i = 0; i<tbhoadon.getRowCount(); i++){
            if(tbhoadon.getValueAt(i, 1).toString().equals(MaVT)){
                tbhoadon.setValueAt(SoLuong, i, 3);
                tbhoadon.setValueAt(DonGia, i, 4);
                tbhoadon.setValueAt(Vat, i, 5);
                tbhoadon.setValueAt(ChuanHoa.ChuyenSoThanhTien(String.valueOf(
                            (Long.parseLong(DonGia)) * (((long) Long.parseLong(SoLuong) * (100 + Integer.parseInt(Vat)))) / 100)), i, 6);
            }
            if(MaVT.equals(maMatHang)){ // Chưa đổi sang mặt hàng khác
                donGiaMatHang = DonGia;
                vatMatHang = Vat;
                soLuongMatHang = SoLuong;
            }
            tinhTongChiPhi();
        }

    }

    public static void tinhTongChiPhi(){
        long tong = 0;
        for(int i = 0; i<tbhoadon.getRowCount(); i++){
            tong += ChuanHoa.ChuyenTienSangSo(tbhoadon.getValueAt(i, 6).toString());
        }
        System.out.println("TONG = "+tong);
        lbtongtien.setText("Tổng: "+ChuanHoa.ChuyenSoThanhTien(String.valueOf(tong))+"đ");
    }
    public static void reSetALL(){
        modelHoaDon.setRowCount(0);
        //datelaphd.setDate(Calendar.getInstance().getTime());
        txtsoluong.setText("");
        txtdongia.setText("");
        txtvat.setText("");
        txtsohd.setText("");
        hoaDonRowSelected = -1;
        lbtongtien.setText("Tổng: 0đ");
        lbthongbao.setText("Bạn chưa chọn mặt hàng nào");
        upDateListVatTu();
    }
}

