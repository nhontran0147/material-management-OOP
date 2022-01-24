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
import java.sql.*;

public class JPanelNhanVien extends JPanel implements MouseListener, KeyListener {
    private static DefaultTableModel model;
    private JLabel lbmanv;
    private JLabel lbhonv;
    private JLabel lbtennv;
    private JLabel lbphai;
    private JTextField txtmanv;
    private JTextField txthonv;
    private JTextField txttennv;

    private JLabel lbPhaiNam;
    private JLabel lbPhaiNu;


    private JTable tbnhanvien;
    private JPanel topContainer;
    private JLabel btnaddNV;
    private JLabel btneditNV;
    private JLabel btnremoveNV;
    private JLabel btnresetNV;

    private static int gioiTinh = -1;
    private static String maNVValueSelected;
    private String tenNValueSelected;
    private String hoNVValueSelected;
    private String phaiSelected;
    private int rowSelected = -1;
    private static JLabel lbthongbao;
    private static boolean danglapHD_NV = false;
    private static int soLuongNhanVien=0;

    public JPanelNhanVien() {
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);
        topContainer = new JPanel();
        topContainer.setLayout(null);
        topContainer.setBounds(10, 10, 980, 270);
        topContainer.setBackground(new Color(60, 70, 73));

        lbmanv = new JLabel("MÃ NHÂN VIÊN: ");
        lbmanv.setBounds(30, 50, 120, 30);
        lbmanv.setForeground(new Color(255, 255, 255));
        txtmanv = new JTextField();
        txtmanv.setBounds(180, 50, 180, 30);
        txtmanv.setBackground(new Color(105, 121, 141));

        txtmanv.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        txtmanv.setForeground(new Color(255, 255, 255));
        txtmanv.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding


        lbhonv = new JLabel("HỌ NHÂN VIÊN: ");
        lbhonv.setBounds(30, 100, 120, 30);
        lbhonv.setForeground(new Color(255, 255, 255));
        txthonv = new JTextField();
        txthonv.setBounds(180, 100, 180, 30);
        txthonv.setBackground(new Color(105, 121, 141));
        txthonv.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        txthonv.setForeground(new Color(255, 255, 255));
        txthonv.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding

        lbtennv = new JLabel("TÊN NHÂN VIÊN: ");
        lbtennv.setBounds(500, 50, 120, 30);
        lbtennv.setForeground(new Color(255, 255, 255));
        txttennv = new JTextField();
        txttennv.setBounds(650, 50, 180, 30);
        txttennv.setBackground(new Color(105, 121, 141));
        txttennv.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        txttennv.setForeground(new Color(255, 255, 255));
        txttennv.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding

        lbphai = new JLabel("PHÁI NHÂN VIÊN: ");
        lbphai.setBounds(500, 100, 120, 30);
        lbphai.setForeground(new Color(255, 255, 255));

        lbPhaiNam = new JLabel("NAM");
        lbPhaiNam.setBounds(650, 100, 80, 30);
        lbPhaiNam.setBackground(new Color(105, 121, 141));
        lbPhaiNam.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        lbPhaiNam.setForeground(new Color(255, 255, 255));
        lbPhaiNam.setOpaque(true);
        lbPhaiNam.setHorizontalAlignment(SwingConstants.CENTER);
        lbPhaiNam.addMouseListener(this);

        lbPhaiNu = new JLabel("NỮ");
        lbPhaiNu.setBounds(750, 100, 80, 30);
        lbPhaiNu.setBackground(new Color(105, 121, 141));
        lbPhaiNu.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        lbPhaiNu.setForeground(new Color(255, 255, 255));
        lbPhaiNu.setOpaque(true);
        lbPhaiNu.setHorizontalAlignment(SwingConstants.CENTER);
        lbPhaiNu.addMouseListener(this);


        lbthongbao = new JLabel("Bạn chưa chọn nhân viên nào");
        lbthongbao.setBounds(0, 650, 950, 40);
        lbthongbao.setFont(new Font("Ubuntu", Font.PLAIN, 15));
        lbthongbao.setHorizontalAlignment(SwingConstants.CENTER);


        btnaddNV = new JLabel("THÊM NHÂN VIÊN");
        btnaddNV.setBounds(150, 200, 160, 40);
        btnaddNV.setBackground(new Color(22, 114, 236));
        btnaddNV.setOpaque(true);
        btnaddNV.setForeground(Color.WHITE);
        btnaddNV.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon add_icon = new ImageIcon(JPanelVatTu.class.getResource("add.png"));
        Image image_add = add_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        add_icon.setImage(image_add);
        btnaddNV.setIcon(add_icon);
        btnaddNV.setIconTextGap(10);

        btneditNV = new JLabel("CHỈNH SỬA");
        btneditNV.setBounds(320, 200, 140, 40);
        btneditNV.setBackground(new Color(42, 185, 48));
        btneditNV.setOpaque(true);
        btneditNV.setForeground(Color.WHITE);
        btneditNV.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon edit_icon = new ImageIcon(JPanelVatTu.class.getResource("edit.png"));
        Image image_edit = edit_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        edit_icon.setImage(image_edit);
        btneditNV.setIcon(edit_icon);
        btneditNV.setIconTextGap(10);


        btnremoveNV = new JLabel("XÓA NHÂN VIÊN");
        btnremoveNV.setBounds(470, 200, 140, 40);
        btnremoveNV.setBackground(new Color(237, 149, 38));
        btnremoveNV.setOpaque(true);
        btnremoveNV.setForeground(Color.WHITE);
        btnremoveNV.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon remove_icon = new ImageIcon(JPanelVatTu.class.getResource("remove.png"));
        Image image_remove = remove_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        remove_icon.setImage(image_remove);
        btnremoveNV.setIcon(remove_icon);
        btnremoveNV.setIconTextGap(10);

        btnresetNV = new JLabel("RESET INPUT");
        btnresetNV.setBounds(620, 200, 140, 40);
        btnresetNV.setBackground(new Color(154, 15, 191));
        btnresetNV.setOpaque(true);
        btnresetNV.setForeground(Color.WHITE);
        btnresetNV.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon reset_icon = new ImageIcon(JPanelVatTu.class.getResource("reset.png"));
        Image image_reset = reset_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        reset_icon.setImage(image_reset);
        btnresetNV.setIcon(reset_icon);
        btnresetNV.setIconTextGap(10);


        String[] columnJT = {"STT", "MÃ SỐ", "HỌ & TÊN LÓT", "TÊN", "PHÁI", "TRẠNG THÁI"};
        String[][] dataJT = {};
        model = new DefaultTableModel(dataJT, columnJT);
        tbnhanvien = new JTable(model);

        GiaoDienQuanLy.changeModelNhanVien(model);

        tbnhanvien.setFillsViewportHeight(true);
        tbnhanvien.setOpaque(true);
        tbnhanvien.setShowGrid(false); // Tắt kẻ bảng
        tbnhanvien.setShowHorizontalLines(false); // Tắt kẻ ngang
        tbnhanvien.setShowVerticalLines(false); // Tắt kẻ dọc
        tbnhanvien.setFont(new Font("SansSerif", Font.PLAIN, 15));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbnhanvien.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Chữ ra giữa bảng
        tbnhanvien.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbnhanvien.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbnhanvien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbnhanvien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbnhanvien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbnhanvien.setRowHeight(30);
        tbnhanvien.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16)); // Set font Header
        tbnhanvien.getTableHeader().setPreferredSize(new Dimension(100, 40)); // Set height

        tbnhanvien.setBackground(new Color(255, 255, 255));
        tbnhanvien.setForeground(new Color(84, 101, 121));

        tbnhanvien.getTableHeader().setBackground(new Color(247, 249, 252));
        tbnhanvien.getTableHeader().setForeground(new Color(105, 121, 141));

        tbnhanvien.setSelectionBackground(Color.YELLOW);
        tbnhanvien.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tbnhanvien.getColumnModel().getColumn(0).setMaxWidth(70);
        tbnhanvien.getColumnModel().getColumn(0).setPreferredWidth(70);

        JScrollPane JS = new JScrollPane(tbnhanvien);
        JS.setBounds(0, 300, 960, 350); // w: 700 bg: 100
        tbnhanvien.setFocusable(false); // Không tạo focus 1 phần tử
        tbnhanvien.setDefaultEditor(Object.class, null); // Không cho edit table

        // Get row and value click
        tbnhanvien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (tbnhanvien.getSelectedRow() > -1) {
                    System.out.println(tbnhanvien.getValueAt(tbnhanvien.getSelectedRow(), 1).toString());
                    maNVValueSelected = tbnhanvien.getValueAt(tbnhanvien.getSelectedRow(), 1).toString();
                    hoNVValueSelected = tbnhanvien.getValueAt(tbnhanvien.getSelectedRow(), 2).toString();
                    tenNValueSelected = tbnhanvien.getValueAt(tbnhanvien.getSelectedRow(), 3).toString();
                    phaiSelected = tbnhanvien.getValueAt(tbnhanvien.getSelectedRow(), 4).toString();
                    lbthongbao.setText("Bạn đang chọn nhân viên: "+maNVValueSelected+" _ "+hoNVValueSelected+" "+tenNValueSelected);
                }
            }
        });

        this.add(JS);
        this.add(lbthongbao);
        this.add(topContainer);
        topContainer.add(lbmanv);
        topContainer.add(lbhonv);
        topContainer.add(lbtennv);
        topContainer.add(lbphai);
        topContainer.add(txtmanv);
        topContainer.add(txthonv);
        topContainer.add(lbPhaiNam);
        topContainer.add(lbPhaiNu);
        topContainer.add(txttennv);
        topContainer.add(btnaddNV);
        topContainer.add(btneditNV);
        topContainer.add(btnresetNV);
        topContainer.add(btnremoveNV);

        btnaddNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnremoveNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btneditNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnresetNV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        // Xu ly su kien
        btnaddNV.addMouseListener(this);
        btnresetNV.addMouseListener(this);
        btnremoveNV.addMouseListener(this);
        btneditNV.addMouseListener(this);
        txtmanv.addKeyListener(this);
        txtmanv.addMouseListener(this);
        txthonv.addKeyListener(this);
        txthonv.addMouseListener(this);
        txttennv.addKeyListener(this);
        txttennv.addMouseListener(this);

    }

    public static void updateList(){
        model.setRowCount(0);
        GiaoDienQuanLy.changeModelNhanVien(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource() == btnaddNV){
            if (Login.getQuyenHanh()!=2){
                JOptionPane.showMessageDialog(null,"Bạn không có quyền thêm nhân viên!");
                return;
            }
            String maNVC = ChuanHoa.ChuanHoa(txtmanv.getText());
            String hoNVC = ChuanHoa.ChuanHoa(txthonv.getText());
            String tenNVC = ChuanHoa.ChuanHoa(txttennv.getText());
            /// can phai kiem tra tai khoan dang nhap
            //
            if (maNVC.equals("") || hoNVC.equals("") || tenNVC.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin nhân viên!");
                return;
            }
            for (int i=0;i<tenNVC.length();++i){
                if (tenNVC.charAt(i)==' '){
                    JOptionPane.showMessageDialog(null,"Tên nhân viên chỉ nhận một chữ, vui lòng nhập lại!");
                    return;
                }
            }
            if(gioiTinh == -1){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính!");
                return;
            }
            if (GiaoDienQuanLy.isTonTaiNhanVien(maNVC)){
                JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại");
            }else{
                int choose = JOptionPane.showConfirmDialog(null,"Bạn có chắc lưu thông tin vào database?");
                if (choose==0){
                    String gtNVC= (gioiTinh==1 ? "Nam":"Nữ");
                    GiaoDienQuanLy.addNhanVien(maNVC,hoNVC,tenNVC,gtNVC);
                    updateList();
                    txthonv.setText(hoNVC);
                    txttennv.setText(tenNVC);
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
                }
            }
        }

        if(e.getSource() == btneditNV){
            if (Login.getQuyenHanh()!=2){
                JOptionPane.showMessageDialog(null,"Bạn không có quyền sửa thông tin nhân viên!");
                return;
            }
            if(maNVValueSelected == null){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để chỉnh sửa");
            }
            else {
                if(danglapHD_NV == true){
                    JOptionPane.showMessageDialog(null, "Vui lòng hoàn thành hóa đơn trước " +
                            "đó sau đó quay lại chỉnh sửa nhân viên");
                    return;
                }
                if (!EditNhanVien.getCheckEditNhanVien())
                    new EditNhanVien(maNVValueSelected, hoNVValueSelected, tenNValueSelected, phaiSelected);
            }
        }

        if(e.getSource() == btnremoveNV){
            if (Login.getQuyenHanh()!=2){
                JOptionPane.showMessageDialog(null,"Bạn không có quyền xóa nhân viên!");
                return;
            }
            if(maNVValueSelected == null){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để xóa");
                return;
            }
            if(danglapHD_NV == true){
                JOptionPane.showMessageDialog(null, "Vui lòng hoàn thành hóa đơn trước " +
                        "đó sau đó quay lại xóa nhân viên");
                return;
            }
            if (GiaoDienQuanLy.isNhanVienDaLapHoaDon(maNVValueSelected))
            {
                int choose = JOptionPane.showConfirmDialog(null, "Nhân viên này đã lập một số hóa đơn nên không thể xóa vĩnh viễn, bạn có muốn chuyển trạng thái nhân viên này?");
                if (choose==0){
                    GiaoDienQuanLy.changeTrangThaiNhanVien(maNVValueSelected,false);
                    updateList();
                }
                return;
            }
            int choose = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa nhân viên: " + maNVValueSelected + "?");
            if (choose == 0) {
                GiaoDienQuanLy.delNhanVien(maNVValueSelected);
                updateList();
                JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
                maNVValueSelected=null;
                lbthongbao.setText("Bạn chưa chọn vật tư nào");
            }
        }

        if (e.getSource() == btnresetNV){
            updateList();
            txtmanv.setText("");
            txttennv.setText("");
            txthonv.setText("");
            gioiTinh=-1;
            lbPhaiNam.setBackground(new Color(105, 121, 141));
            lbPhaiNu.setBackground(new Color(105, 121, 141));
            lbthongbao.setText("Bạn chưa chọn vật tư nào");
            maNVValueSelected = null;
        }

        if(e.getSource() == lbPhaiNam){
            lbPhaiNam.setBackground(new Color(42, 185, 48));
            lbPhaiNu.setBackground(new Color(105, 121, 141));
            gioiTinh = 1;
        }
        if(e.getSource() == lbPhaiNu){
            lbPhaiNu.setBackground(new Color(42, 185, 48));
            lbPhaiNam.setBackground(new Color(105, 121, 141));
            gioiTinh = 0;
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
        if (e.getSource() == btnaddNV) {
            btnaddNV.setBackground(new Color(255, 209, 30));
        }
        if (e.getSource() == btneditNV) {
            btneditNV.setBackground(new Color(255, 209, 30));
        }
        if (e.getSource() == btnremoveNV) {
            btnremoveNV.setBackground(new Color(255, 209, 30));
        }
        if (e.getSource() == btnresetNV) {
            btnresetNV.setBackground(new Color(255, 209, 30));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == btnaddNV) {
            btnaddNV.setBackground(new Color(22, 114, 236));
        }
        if (e.getSource() == btneditNV) {
            btneditNV.setBackground(new Color(42, 185, 48));
        }
        if (e.getSource() == btnremoveNV) {
            btnremoveNV.setBackground(new Color(237, 149, 38));
        }
        if (e.getSource() == btnresetNV) {
            btnresetNV.setBackground(new Color(154, 15, 191));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == txtmanv) {
            if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && txtmanv.getText().length() < 9) || e.getKeyCode() == 8 || e.getKeyCode() == 127) {
                txtmanv.setEditable(true);
            } else {
                txtmanv.setEditable(false);
            }
        }

        if (e.getSource() == txttennv)  //can fix them
            if ((((e.getKeyCode()>=65 && e.getKeyCode()<=90) || e.getKeyCode()==0
                    || e.getKeyCode() == 32 || e.getKeyCode() == 16) && txttennv.getText().length() < 45) || e.getKeyCode() == 8 || e.getKeyCode() == 127) {
                txttennv.setEditable(true);
            } else txttennv.setEditable(false);

        if (e.getSource() == txthonv) {
            if ((((e.getKeyCode()>=65 && e.getKeyCode()<=90) || e.getKeyCode()==0
                    || e.getKeyCode() == 32 || e.getKeyCode() == 16) && txthonv.getText().length() < 45) || e.getKeyCode() == 8 || e.getKeyCode() == 127) {
                txthonv.setEditable(true);
            } else txthonv.setEditable(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void setThongBao(String thongbao) {
        JPanelNhanVien.lbthongbao.setText(thongbao);
    }

    public static void setMaNVValueSelected(String maNVValueSelected) {
        JPanelNhanVien.maNVValueSelected = maNVValueSelected;
    }

    public static void setDanglapHD_NV(boolean danglapHD_NV) {
        JPanelNhanVien.danglapHD_NV = danglapHD_NV;
    }

    public static void reSetAll(){
        lbthongbao.setText("Bạn chưa chọn nhân viên nào");
        maNVValueSelected = null;
        updateList();
    }
    public static int getSoLuongNhanVien(){
        return soLuongNhanVien;
    }
}

