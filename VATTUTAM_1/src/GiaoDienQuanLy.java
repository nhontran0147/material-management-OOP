import DoiTuong.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import ThaoTacChuoi.ChuyenDoiXau;

// SQL Sai kiểu dữ liệu
// Xóa thì cho null
public class GiaoDienQuanLy extends JFrame implements MouseListener {
    //
    private static ArrayList<NhanVien> nhanVienArrayList;
    private static ArrayList<VatTu> vatTuArrayList;
    private static ArrayList<User> userArrayList;
    private int quyenHanh;
    GiaoDienQuanLy() {
        this.setTitle("Giao Diện Quản Lý");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.con =  ConnectSQL.getCon();

        //
        vatTuArrayList = new ArrayList<>();
        nhanVienArrayList = new ArrayList<>();
        userArrayList = new ArrayList<>();
        loadDataToArrayList();
        quyenHanh = Login.getQuyenHanh();
        //
        pn00 = new JPanel();
        pn00.setBounds(0, 0, WIDTH, 50);
        pn00.setBackground(new Color(255, 85, 85));
        lb00 = new JLabel("QUẢN LÝ VẬT TƯ");
        lb00.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        lb00.setForeground(Color.WHITE);
        pn00.add(lb00);


        String chucvu  = "";
        if(Login.getQuyenHanh() == 2) {
            chucvu = "Admin";
        }else if (Login.getQuyenHanh()==1) chucvu = "Quản lí";
        else chucvu = "Nhân viên";
        JLabel lb001 = new JLabel("XIN CHÀO, "+Login.getUserName()+". ("+chucvu + ")");
        lb001.setForeground(new Color(255, 85, 85));
        lb001.setBounds(20, 20, 210, 100);
        lb001.setBackground(new Color(255, 209, 30));
        lb001.setOpaque(true);
        ImageIcon icon00 = new ImageIcon(GiaoDienQuanLy.class.getResource("man.png"));
        Image newimg00 = icon00.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        lb001.setHorizontalAlignment(JLabel.CENTER);
        lb001.setHorizontalTextPosition(JLabel.CENTER);
        lb001.setVerticalTextPosition(JLabel.BOTTOM);
        lb001.setVerticalTextPosition(JLabel.BOTTOM);
        icon00.setImage(newimg00);
        lb001.setIcon(new NoScalingIcon(icon00));
        lb001.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));


        pn01 = new JPanel();
        pn01.setBackground(new Color(226, 248, 227));
        pn01.setBounds(0, 60, 250, HEIGHT - 120); // 0 // 50 // 250 // HEIGHT
        pn01.setLayout(null);


        pn01.add(lb001);

        pn02 = new JPanel();
        pn02.setBackground(new Color(10, 154, 254));
        pn02.setBounds(260, 60, WIDTH - 280, HEIGHT - 120); // 250 // 50 // - 250 // HEIGHT
        pn02.setLayout(null);


        pn03 = new JPanel();
        pn03.setBounds(20, 200, 210, 60); // 250- 60
        pn03.setLayout(null);
        lb01 = new JLabel("QUẢN LÝ VẬT TƯ     ");
        lb01.setSize(210, 60);
//        lb01.setBackground(new Color(254, 147, 13));
        lb01.setBackground(new Color(33, 205, 65));
        lb01.setOpaque(true);
        lb01.setHorizontalAlignment(SwingConstants.CENTER);
        lb01.setForeground(Color.WHITE);

        ImageIcon icon01 = new ImageIcon(GiaoDienQuanLy.class.getResource("material.png"));
        Image newimg01 = icon01.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
        icon01.setImage(newimg01);
        lb01.setIcon(new NoScalingIcon(icon01));
        lb01.setIconTextGap(10);
        pn03.add(lb01);

        pn04 = new JPanel();
        pn04.setBounds(20, 270, 210, 60);
        pn04.setLayout(null);
        lb02 = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lb02.setSize(210, 60);
//        lb02.setBackground(new Color(89, 177, 93));
        lb02.setBackground(new Color(33, 205, 65));
        lb02.setOpaque(true);
        lb02.setHorizontalAlignment(SwingConstants.CENTER);
        lb02.setForeground(Color.WHITE);

        ImageIcon icon02 = new ImageIcon(GiaoDienQuanLy.class.getResource("student.png"));
        Image newimg02 = icon02.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        icon02.setImage(newimg02);
        lb02.setIcon(new NoScalingIcon(icon02));
        lb02.setIconTextGap(10);
        pn04.add(lb02);

        if(quyenHanh != 2) {
            pn05 = new JPanel();
            pn05.setBounds(20, 340, 210, 60);
            pn05.setLayout(null);
            lb03 = new JLabel("QUẢN LÝ HÓA ĐƠN");
            lb03.setSize(210, 60);
            lb03.setOpaque(true);
            lb03.setBackground(new Color(33, 205, 65));
            lb03.setHorizontalAlignment(SwingConstants.CENTER);
            lb03.setForeground(Color.WHITE);

            ImageIcon icon03 = new ImageIcon(GiaoDienQuanLy.class.getResource("invoice.png"));
            Image newimg03 = icon03.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            icon03.setImage(newimg03);
            lb03.setIcon(new NoScalingIcon(icon03));
            lb03.setIconTextGap(10);
            pn05.add(lb03);
            pn01.add(pn05);
            lb03.addMouseListener(this);
        }


        pn031 = new JPanel();
        if(quyenHanh == 2){
            pn031.setBounds(20, 340, 210, 60);
        }else {
            pn031.setBounds(20, 410, 210, 60);
        }
        pn031.setBackground(Color.magenta);
        pn031.setLayout(null);
        lb031 = new JLabel("QUẢN LÝ THỐNG KÊ");
        lb031.setSize(210, 60);
        lb031.setBackground(new Color(33, 205, 65));
        lb031.setOpaque(true);
        lb031.setHorizontalAlignment(SwingConstants.CENTER);
        lb031.setForeground(Color.WHITE);

        ImageIcon icon04 = new ImageIcon(GiaoDienQuanLy.class.getResource("overview.png"));
        Image newimg04 = icon04.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        icon04.setImage(newimg04);
        lb031.setIcon(new NoScalingIcon(icon04));
        lb031.setIconTextGap(10);
        pn031.add(lb031);


        if(quyenHanh == 2) {
            lb032 = new JLabel("ĐĂNG KÝ TÀI KHOẢN");
            lb032.setBounds(20, 410, 210, 60);
            lb032.setBackground(new Color(33, 205, 65));
            lb032.setOpaque(true);
            lb032.setHorizontalAlignment(SwingConstants.CENTER);
            lb032.setForeground(Color.WHITE);

            ImageIcon icon05 = new ImageIcon(GiaoDienQuanLy.class.getResource("signup.png"));
            Image newimg05 = icon05.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            icon05.setImage(newimg05);
            lb032.setIcon(new NoScalingIcon(icon05));
            lb032.setIconTextGap(10);
            lb032.addMouseListener(this);
            pn01.add(lb032);
        }
        logout = new JLabel("ĐĂNG XUẤT");
        logout.setBounds(50, 600, 150, 40);
        logout.setHorizontalAlignment(SwingConstants.CENTER);
        logout.setOpaque(true);
        logout.setForeground(Color.WHITE);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logout.setBackground(new Color(255, 85, 85));
        pn01.add(logout);
        logout.addMouseListener(this);

        tbp01 = new JTabbedPane();
        tbp01.setBorder(null);
        tbp01.setBounds(-10, -50, WIDTH - 200, HEIGHT);
        pn06 = new JPanelVatTu();
        pn07 = new JPanelNhanVien();
        pn08 = new JPanelHoaDon();
        pn09 = new JPanelThongKe();
        lb04 = new JLabel("Tab1");
        lb05 = new JLabel("Tab2");
        lb06 = new JLabel("Tab3");
        lb07 = new JLabel("Tab4");
        pn06.add(lb04);
        pn07.add(lb05);
        pn08.add(lb06);


        tbp01.add("", pn06);
        tbp01.add("", pn07);
        tbp01.add("", pn08);
        tbp01.add("", pn09);

        this.add(pn00);
        this.add(pn01);
        pn01.add(pn03);
        pn01.add(pn04);
//        pn01.add(pn05);
        pn01.add(pn031);
        this.add(pn02);
        pn02.add(tbp01);

        lb01.addMouseListener(this);
        lb02.addMouseListener(this);
        lb031.addMouseListener(this);


        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == lb01) {
            tbp01.setSelectedIndex(0);

            System.out.println(vatTuArrayList.get(0).getTenVatTu());
        } else if (e.getSource() == lb02) {
            tbp01.setSelectedIndex(1);
        } else if (e.getSource() == lb03) {
            JPanelHoaDon.upDateListVatTu();
            tbp01.setSelectedIndex(2);
        } else if (e.getSource() == lb031) {
           JPanelThongKe.setSoLuongVT(getSoLuongVatTu());
           JPanelThongKe.setSoLuongNV(getSoLuongNhanVien());
           JPanelThongKe.setSoLuongHD(getSoLuongHoaDon());
            tbp01.setSelectedIndex(3);
        }else if(e.getSource() == lb032){
            if (!Signup.getCheckSignup())
                new Signup();
        } else if(e.getSource() == logout){ // LOGOUT
            this.dispose();
            JPanelVatTu.setMaVTValueSelected(null);
            JPanelNhanVien.setMaNVValueSelected(null);
            JPanelVatTu.setDanglapHD_VT(false);
            JPanelNhanVien.setDanglapHD_NV(false);
           // JPanelHoaDon.setManvchoosed(null);
            new Login();
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
        if (e.getSource() == lb01) {
            lb01.setBackground(new Color(124, 218, 73));
        } else if (e.getSource() == lb02) {
            lb02.setBackground(new Color(124, 218, 73));
        } else if (e.getSource() == lb03) {
            lb03.setBackground(new Color(124, 218, 73));
        } else if (e.getSource() == lb031) {
            lb031.setBackground(new Color(124, 218, 73));
        }else if (e.getSource() == lb032) {
            lb032.setBackground(new Color(124, 218, 73));
        }else if (e.getSource() == logout) {
            logout.setBackground(new Color(124, 218, 73));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == lb01) {
            lb01.setBackground(new Color(33, 205, 65));
        } else if (e.getSource() == lb02) {
            lb02.setBackground(new Color(33, 205, 65));
        } else if (e.getSource() == lb03) {
            lb03.setBackground(new Color(33, 205, 65));
        } else if (e.getSource() == lb031) {
            lb031.setBackground(new Color(33, 205, 65));
        }else if (e.getSource() == lb032) {
            lb032.setBackground(new Color(33, 205, 65));
        }else if (e.getSource() == logout) { //// LOGOUT
            logout.setBackground(new Color(255, 85, 85));
        }
    }

    public void loadDataToArrayList() {
        String queryVatTu = "SELECT * FROM VATTU ORDER BY TENVT,MAVT";
        try {
            PreparedStatement pre = con.prepareStatement(queryVatTu);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                vatTuArrayList.add(new VatTu(rs.getString("MaVT"), rs.getString("TenVT"), rs.getInt("SoLuongTon"), rs.getString("DonViTinh")));
            }
            pre.close();
            System.out.println("LOAD THÀNH CÔNG VẬT TƯ o giao dien login");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR LOAD in login");
        }
        String queryNhanVien = "SELECT * FROM NHANVIEN ORDER BY TENNV,HONV";
        try {
            PreparedStatement pre = con.prepareStatement(queryNhanVien);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                nhanVienArrayList.add(new NhanVien(rs.getString("MaNV"), rs.getString("HoNV"), rs.getString("TenNV"), rs.getString("Phai"), rs.getBoolean("TrangThai")));
            }
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i=0;i<nhanVienArrayList.size();++i){
            String queryHoaDon = "SELECT * FROM HOADON WHERE MANV LIKE '" + nhanVienArrayList.get(i).getMaNhanVien() + "' ORDER BY NgayLap";
            try {
                PreparedStatement pre = con.prepareStatement(queryHoaDon);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    HoaDon hoaDon= new HoaDon(rs.getString("SoHD"), rs.getDate("NgayLap"), rs.getString("Loai"));
                    String queryChiTiet = "SELECT * FROM CTHOADON WHERE SOHD LIKE '" + hoaDon.getSoHoaDon() + "'";
                    try {
                        PreparedStatement pre1 = con.prepareStatement(queryChiTiet);
                        ResultSet rs1 = pre1.executeQuery();
                        while (rs1.next()) {
//                            System.out.println("MaVT"+ rs1.getString("Mavt"));
                            hoaDon.addChiTiet(new ChiTietHoaDon(rs1.getString("SoHD"), rs1.getString("MaVT"), rs1.getInt("SoLuong"), rs1.getInt("DonGia"), rs1.getInt("Vat")));
                        }
                        pre1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                   // System.out.println(String.valueOf(hoaDon.getNgayLap())+"hehe");
                    nhanVienArrayList.get(i).addHoaDon(hoaDon);
                }
                pre.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }
//            System.out.println("MaNV:" + nhanVienArrayList.get(i).getMaNhanVien());
//            for(int j=0;j<nhanVienArrayList.get(i).getSoLuongHoaDon();++j){
//                System.out.println("So HD:"+nhanVienArrayList.get(i).getHoaDonArrayList().get(j).getSoHoaDon());
//                for(int k=0;k<nhanVienArrayList.get(i).getHoaDonArrayList().get(j).getChiTietHoaDon().size();++k){
//                    System.out.println("MaVT:"+nhanVienArrayList.get(i).getHoaDonArrayList().get(j).getChiTietHoaDon().get(k).getMaVatTu());
//                }
//            }
        }
        String queryUser = "SELECT * FROM USERS";
        try {
            PreparedStatement pre = con.prepareStatement(queryUser);
            ResultSet res = pre.executeQuery();
            while (res.next()){
                userArrayList.add(new User(res.getString("UserName"),res.getString("Pass"),res.getInt("Quyen")));
            }
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<NhanVien> getNhanVienArrayList(){
        return nhanVienArrayList;
    }

    public static void addVatTu(String maVatTu, String tenVatTu, int soLuongTon, String donViTinh){
        String queryVatTu = "INSERT INTO VATTU VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(queryVatTu);
            pre.setString(1, maVatTu);
            pre.setString(2, tenVatTu);
            pre.setInt(3, soLuongTon);
            pre.setString(4, donViTinh);
            pre.executeUpdate();
            pre.close();
            vatTuArrayList.add(new VatTu(maVatTu,tenVatTu,soLuongTon,donViTinh));
            vatTuArrayList.sort((Comparator.comparing(staff -> ((VatTu)staff).getTenVatTuKhongDau()).thenComparing(staff -> ((VatTu)staff).getMaVatTu())));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean isVatTuInHoaDon(String maVatTu){
        for (int i=0;i<nhanVienArrayList.size();++i){
            ArrayList<HoaDon> hoaDonArrayList = nhanVienArrayList.get(i).getHoaDonArrayList();
            for(int j=0;j<hoaDonArrayList.size();++j){
                ArrayList<ChiTietHoaDon> chiTietHoaDonArrayList = hoaDonArrayList.get(j).getChiTietHoaDon();
                for (int k=0;k < chiTietHoaDonArrayList.size();++k){
                    if (chiTietHoaDonArrayList.get(k).getMaVatTu().equals(maVatTu))
                        return true;
                }
            }
        }
        return false;
    }
    public static void delVatTu(String maVatTu){
        for (VatTu vatTu: vatTuArrayList){
            if (vatTu.getMaVatTu().equals(maVatTu)){
                String sql = "DELETE FROM VATTU WHERE " + "MAVT like '" + vatTu.getMaVatTu() + "'";
                try {
                    PreparedStatement pre = con.prepareStatement(sql);
                    pre.executeUpdate();
                    pre.close();
                    vatTuArrayList.remove(vatTu);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return;
            }
        }
    }
    public static void changeVatTu(String maVatTu, String tenVatTu, String donViTinh){
        for (VatTu vatTu: vatTuArrayList){
            if (vatTu.getMaVatTu().equals(maVatTu)){
                vatTu.setTenVatTu(tenVatTu);
                vatTu.setDonViTinh(donViTinh);
                String queryVatTu = "UPDATE VATTU " +
                        "SET TenVT = N'"+vatTu.getTenVatTu()+ "', DONVITINH = N'"+vatTu.getDonViTinh() +
                        "' WHERE MAVT like '"+ vatTu.getMaVatTu() + "'";
                try {
                    PreparedStatement pre = con.prepareStatement(queryVatTu);
                    pre.executeUpdate();
                    pre.close();
                    vatTuArrayList.sort((Comparator.comparing(staff -> ((VatTu)staff).getTenVatTuKhongDau()).thenComparing(staff -> ((VatTu)staff).getMaVatTu())));
                    JPanelVatTu.upDateList();
                    JPanelHoaDon.upDateListVatTu();
                    JPanelVatTu.setMaVTValueSelected(null);
                    JPanelVatTu.setThongBao("Bạn chưa chọn vật tư nào");
                    JPanelHoaDon.setVatTuNameSelected(null);
                    JPanelHoaDon.setVatTuValueSelected(null);
                   // JPanelHoaDon.setMavtchoosed("NULL");
                    JOptionPane.showMessageDialog(null, "Chỉnh sửa vật tư thành công");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return;
            }
        }
    }
    public static void setModelVatTu(DefaultTableModel model,boolean kieu){
        if (kieu){
            int stt=1;
            for (VatTu vatTuTemp: vatTuArrayList){
                model.addRow(vatTuTemp.toObjectsSTT(stt));
                stt++;
            }
        }else{
            for (VatTu vatTuTemp: vatTuArrayList){
                model.addRow(vatTuTemp.toObjects());
            }
        }

    }
    public static boolean checkVatTuDaTonTai(String maVatTu){
        for (VatTu vatTu: vatTuArrayList){
            if (vatTu.getMaVatTu().equals(maVatTu))
                return true;
        }
        return false;
    }
    public static int getSoLuongVatTu(){
        return vatTuArrayList.size();
    }
    public static int getSoLuongHoaDon(){
        int sum=0;
        for(NhanVien nhanVien: nhanVienArrayList){
            sum+=nhanVien.getSoLuongHoaDon();
        }
        return sum;
    }
    public static int getSoLuongNhanVien(){
        return nhanVienArrayList.size();
    }
    public static void changeModelNhanVien(DefaultTableModel model){
        int stt=1;
        for (NhanVien nhanVien: nhanVienArrayList){
            model.addRow(nhanVien.toObjectsSTT(stt));
            stt++;
        }
    }
    public static boolean isTonTaiNhanVien(String maNhanVien){
        for (NhanVien nhanVien:nhanVienArrayList){
            if (nhanVien.getMaNhanVien().equals(maNhanVien))
                    return true;
        }
        return false;
    }
    public static void addNhanVien(String maNhanVien, String hoNhanVien, String tenNhanVien, String phaiNhanVien){
        String sql = "INSERT INTO NHANVIEN VALUES (?, ?, ?, ?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, maNhanVien);
            pre.setString(2, hoNhanVien);
            pre.setString(3, tenNhanVien);
            pre.setString(4, phaiNhanVien);
            pre.setString(5,"1");
            pre.executeUpdate();
            pre.close();
            nhanVienArrayList.add(new NhanVien(maNhanVien,hoNhanVien,tenNhanVien,phaiNhanVien,true));
            nhanVienArrayList.sort(Comparator.comparing(staff -> ((NhanVien)staff).getTenNhanVienKhongDau()).thenComparing(staff -> ((NhanVien)staff).getHoNhanVienKhongDau()));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static boolean isNhanVienDaLapHoaDon(String maNhanVien){
        for (int i=0;i<nhanVienArrayList.size();++i){
            if (nhanVienArrayList.get(i).getMaNhanVien().equals(maNhanVien))
                if (nhanVienArrayList.get(i).getSoLuongHoaDon()>0)
                    return true;
                else return false;
        }
        return false;
    }
    public static void changeNhanVien(String maNhanVien, String hoNhanVien, String tenNhanVien,String phaiNhanVien){
        for (NhanVien nhanVien:nhanVienArrayList){
            if (nhanVien.getMaNhanVien().equals(maNhanVien)){
                nhanVien.setHoNhanVien(hoNhanVien);
                nhanVien.setTenNhanVien(tenNhanVien);
                nhanVien.setPhaiNhanVien(phaiNhanVien);
                try {
                    Statement stmt = con.createStatement();
                    String sql = "UPDATE NHANVIEN " +
                            "SET TenNV = N'"+ nhanVien.getTenNhanVien()+ "', Phai = N'"+ nhanVien.getPhaiNhanVien()+"', "+
                            "HONV = N'"+ nhanVien.getHoNhanVien() +
                            "' WHERE MANV like '"+ nhanVien.getMaNhanVien() + "'";
                    stmt.executeUpdate(sql);
                    stmt.close();
                    JPanelNhanVien.setMaNVValueSelected(null);
                    JOptionPane.showMessageDialog(null, "Chỉnh sửa nhân viên thành công");
                    nhanVienArrayList.sort(Comparator.comparing(staff -> ((NhanVien)staff).getTenNhanVienKhongDau()).thenComparing(staff -> ((NhanVien)staff).getHoNhanVienKhongDau()));
                    JPanelNhanVien.updateList();
//                    JPanelHoaDon.upDateListNhanVien();
                    JPanelNhanVien.setThongBao("Bạn chưa chọn nhân viên nào");
//                    JPanelHoaDon.setNhanVienValueSelected(null);
                //    JPanelHoaDon.setManvchoosed("NULL");
                    return;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static void changeTrangThaiNhanVien(String maNhanVien,boolean trangThai){
        if (trangThai==false){
            for (User user: userArrayList){
                if(user.getUserName().equals(maNhanVien)){
                    String sql = "DELETE FROM USERS WHERE MANV LIKE '" + user.getUserName() + "'";
                    try{
                        PreparedStatement pre = con.prepareStatement(sql);
                        pre.executeUpdate();
                        pre.close();
                        userArrayList.remove(user);
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        for(NhanVien nhanVien: nhanVienArrayList){
            if (nhanVien.getMaNhanVien().equals(maNhanVien)){
                nhanVien.setTrangThai(trangThai);
                try {
                    Statement stmt = con.createStatement();
                    String tt=(trangThai==true  ? "1":"0");
                    String sql = "UPDATE NHANVIEN " +
                            "SET TrangThai = '" +tt+
                            "' WHERE MANV like '"+ maNhanVien + "'";
                    stmt.executeUpdate(sql);
                    stmt.close();
                    JPanelNhanVien.setMaNVValueSelected(null);
                    JPanelNhanVien.updateList();
//                    JPanelHoaDon.upDateListNhanVien();
                    JPanelNhanVien.setThongBao("Bạn chưa chọn nhân viên nào");
                    //JPanelHoaDon.setNhanVienValueSelected(null);
                 //   JPanelHoaDon.setManvchoosed("NULL");
                    return;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static void delNhanVien(String maNhanVien){
        for (User user: userArrayList){
            if(user.getUserName().equals(maNhanVien)){
                String sql = "DELETE FROM USERS WHERE USERNAME LIKE '" + user.getUserName() + "'";
                try{
                    PreparedStatement pre = con.prepareStatement(sql);
                    pre.executeUpdate();
                    pre.close();
                    userArrayList.remove(user);
                    break;
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        for (NhanVien nhanVien: nhanVienArrayList){
            if(nhanVien.getMaNhanVien().equals(maNhanVien)){
                String sql = "DELETE FROM NHANVIEN WHERE MANV LIKE '" + nhanVien.getMaNhanVien() + "'";
                try {
                    PreparedStatement pre = con.prepareStatement(sql);
                    pre.executeUpdate();
                    pre.close();
                    nhanVienArrayList.remove(nhanVien);
                  //  JPanelHoaDon.setNhanVienValueSelected(null);
                  //  JPanelHoaDon.setManvchoosed("NULL");
//                    JPanelHoaDon.upDateListNhanVien();
                    return;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }
    public static boolean isUserTonTai(String maNV){
        for (User user: userArrayList){
            if (user.getUserName().equals(maNV))
                return true;
        }
        return false;
    }
    public static void changeUser(String maNv, String passWord,int quyen){
        for (User user: userArrayList){
            if (user.getUserName().equals(maNv)){
                user.setPassWord(passWord);
                String sql = "UPDATE USERS SET PASS ='" + passWord + "', QUYEN = " + String.valueOf(quyen) + " WHERE Username LIKE '" + maNv + "'";
                try {
                    PreparedStatement pre = con.prepareStatement(sql);
                    pre.executeUpdate();
                    pre.close();
                    return;
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static void addUser(User user){
        userArrayList.add(user);
        String sql = "INSERT INTO USERS VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, user.getUserName());
            pre.setString(2, user.getPassWord());
            pre.setInt(3, user.getQuyen());
            pre.executeUpdate();
            pre.close();
            JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi tạo tài khoản");
            ex.printStackTrace();
        }
    }
    public static boolean soSanhThoiGian(String timeBD,String ngLap){
        String[] arrBD = timeBD.split("/");
        String[] arrNL = ngLap.split("/");
        for (int i=0;i<arrBD.length;++i){
            if (Integer.parseInt(arrBD[i]) < Integer.parseInt(arrNL[i]))
                return true;
            else if(Integer.parseInt(arrBD[i]) > Integer.parseInt(arrNL[i])) return false;
        }
        return true;
    }
    public static void napModelThongKeHoaDon(DefaultTableModel model,String timeBD,String timeKT){
        ArrayList<HoTroHD> hoaDonArrayList = new ArrayList<>();
        for (NhanVien nhanVien: nhanVienArrayList){
            for (HoaDon hoaDon: nhanVien.getHoaDonArrayList()){
                SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
                String ngLap = fm.format(hoaDon.getNgayLap());
                if (soSanhThoiGian(timeBD,ngLap) && soSanhThoiGian(ngLap,timeKT))
                    hoaDonArrayList.add(new HoTroHD(hoaDon.getSoHoaDon(),hoaDon.getNgayLap(),hoaDon.getLoaiHoaDon(),nhanVien.getHoNhanVien()+" "+nhanVien.getTenNhanVien(),hoaDon.getTriGia()));
            }
        }
        hoaDonArrayList.sort(Comparator.comparing(staff ->((HoTroHD)staff).getNgayLap()).thenComparing(staff -> ((HoTroHD)staff).getSoHoaDon()));
        int stt=1;
        for (HoTroHD hoTroHD: hoaDonArrayList){
            model.addRow(hoTroHD.toObjects(stt));
            stt++;
        }
    }
    public static void napModelTop(DefaultTableModel model,String timeBD, String timeKT){
        ArrayList<HoTroTop> hoTroTopArrayList = new ArrayList<>();
        for (VatTu vatTu: vatTuArrayList){
            hoTroTopArrayList.add(new HoTroTop(vatTu.getMaVatTu(),vatTu.getTenVatTu(),0,0));
        }
        for (NhanVien nhanVien: nhanVienArrayList){
            for (HoaDon hoaDon: nhanVien.getHoaDonArrayList()){
                SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
                String ngLap = fm.format(hoaDon.getNgayLap());
                if (soSanhThoiGian(timeBD,ngLap) && soSanhThoiGian(ngLap,timeKT) && hoaDon.getLoaiHoaDon().equals("X")){
                    for (ChiTietHoaDon chiTietHoaDon: hoaDon.getChiTietHoaDon()){
                        for(HoTroTop hoTroTop:hoTroTopArrayList){
                            if (chiTietHoaDon.getMaVatTu().equals(hoTroTop.getMaVatTu())){
                                hoTroTop.addSoLuong(chiTietHoaDon.getSoLuong());
                                hoTroTop.addTienThu(chiTietHoaDon.getTong());
                                break;
                            }
                        }
                    }
                }
            }
        }
        hoTroTopArrayList.sort(Comparator.comparing(staff ->((HoTroTop)staff).getTienThu()).reversed());
        int stt=1;
        int dem=0;
        while(stt<=10 && dem<hoTroTopArrayList.size() && hoTroTopArrayList.get(dem).getTienThu()>0){
            model.addRow(hoTroTopArrayList.get(dem).toObjects(stt));
            stt++;
            dem++;
        }
    }
    public static NhanVien getThongTinNhanVienlap(){
        String maNV=Login.getUserName();
        int vt=0;
        for (int i=0;i<nhanVienArrayList.size();++i){
            if(nhanVienArrayList.get(i).getMaNhanVien().equals(maNV)){
                vt=i;
                break;
            }
        }
        return nhanVienArrayList.get(vt);
    }
    public static void napModelTimKiem(DefaultTableModel model, String timKiem){
        timKiem=timKiem.toLowerCase();
        for (VatTu vatTu: vatTuArrayList){
            if (vatTu.getMaVatTu().contains(timKiem) || vatTu.getTenVatTu().toLowerCase().contains(timKiem))
                model.addRow(vatTu.toObjects());
        }
    }
    public static void addHoaDon(String maNV,HoaDon hoaDon){
        for(NhanVien nhanVien:nhanVienArrayList){
            if (nhanVien.getMaNhanVien().equals(maNV)){
                nhanVien.addHoaDon(hoaDon);
                break;
            }
        }
    }
    public static void updateVatTu(String congTru,JTable tbp){
        for(int i=0;i<tbp.getRowCount();++i){
            for(VatTu vatTu:vatTuArrayList){
                if(vatTu.getMaVatTu().equals(tbp.getValueAt(i,1).toString())){
                    if (congTru=="+"){
                        vatTu.addSoLuongTon(Integer.parseInt(tbp.getValueAt(i,3).toString()));
                    }else vatTu.decSoLuongTon(Integer.parseInt(tbp.getValueAt(i,3).toString()));
                    break;
                }
            }
        }
    }

    private final int HEIGHT = 800;
    private final int WIDTH = 1200;
    private JPanel pn00;
    private JPanel pn01; // Vat Tu
    private JPanel pn02; // Nhan Vien
    private JPanel pn03; // Hoa Don
    private JPanel pn031; // Thong Ke
    private JPanel pn04;
    private JPanel pn05;
    private JPanel pn06; // tab1
    private JPanel pn07; // tab2
    private JPanel pn08; // tab3
    private JPanel pn09; // tab4
    private JLabel lb00;
    private JLabel lb01;
    private JLabel lb02;
    private JLabel lb03;
    private JLabel lb031; // LB Thong ke
    private JLabel lb032;
    private JLabel lb04; // name tab1
    private JLabel lb05; // name tab2
    private JLabel lb06; // name tab3
    private JLabel lb07; // name tab4
    private JTabbedPane tbp01;
    private static Connection con;
    private JLabel logout;
}

//class TESTPRO {
//    public static void main(String[] args) {
//        new GiaoDienQuanLy();
//    }
//}
