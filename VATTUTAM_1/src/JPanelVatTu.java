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

public class JPanelVatTu extends JPanel implements KeyListener, MouseListener {
    private JTable tbvattu;
    private JLabel lbmavt;
    private JLabel lbtenvt;
    private JLabel lbsoluongton;
    private JLabel lbdonvi;
    private JTextField txtmavt;
    private JTextField txttenvt;
    private JTextField txtsoluongton;
    private JTextField txtdonvi;
    private JPanel topContainer;
    private static DefaultTableModel model;
    private JLabel btnaddVT;
    private JLabel btneditVT;
    private JLabel btnremoveVT;
    private JLabel btnresetVT;
    private static String maVTValueSelected;
    private String tenVTValueSelected;
    private String donViValueSelected;
    private String soLuongSelected;
    private int rowSelected = -1;
    private static JLabel lbthongbao;
    private static boolean danglapHD_VT = false;

    public JPanelVatTu() {
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);

        topContainer = new JPanel();
        topContainer.setLayout(null);
        topContainer.setBounds(10, 10, 980, 270);
        topContainer.setBackground(new Color(60, 70, 73));


        lbmavt = new JLabel("MÃ VẬT TƯ: ");
        lbmavt.setBounds(30, 50, 120, 30);
        lbmavt.setForeground(new Color(255, 255, 255));
        txtmavt = new JTextField();
        txtmavt.setBounds(180, 50, 180, 30);
        txtmavt.setBackground(new Color(105, 121, 141));

        txtmavt.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        txtmavt.setForeground(new Color(255, 255, 255));
        txtmavt.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding

        lbtenvt = new JLabel("TÊN VẬT TƯ: ");
        lbtenvt.setBounds(30, 100, 120, 30);
        lbtenvt.setForeground(new Color(255, 255, 255));
        txttenvt = new JTextField();
        txttenvt.setBounds(180, 100, 180, 30);
        txttenvt.setBackground(new Color(105, 121, 141));
        txttenvt.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        txttenvt.setForeground(new Color(255, 255, 255));
        txttenvt.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding

        lbsoluongton = new JLabel("SỐ LƯỢNG TỒN: ");
        lbsoluongton.setBounds(500, 50, 120, 30);
        lbsoluongton.setForeground(new Color(255, 255, 255));
        txtsoluongton = new JTextField();
        txtsoluongton.setBounds(650, 50, 180, 30);
        txtsoluongton.setBackground(new Color(105, 121, 141));
        txtsoluongton.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        txtsoluongton.setForeground(new Color(255, 255, 255));
        txtsoluongton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding

        lbdonvi = new JLabel("ĐƠN VỊ TÍNH: ");
        lbdonvi.setBounds(500, 100, 120, 30);
        lbdonvi.setForeground(new Color(255, 255, 255));
        txtdonvi = new JTextField();
        txtdonvi.setBounds(650, 100, 180, 30);
        txtdonvi.setBackground(new Color(105, 121, 141));
        txtdonvi.setFont(new Font("Ubuntu", Font.PLAIN, 16));
        txtdonvi.setForeground(new Color(255, 255, 255));
        txtdonvi.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set padding


        lbthongbao = new JLabel("Bạn chưa chọn vật tư nào");
        lbthongbao.setBounds(0, 650, 950, 40);
        lbthongbao.setFont(new Font("Ubuntu", Font.PLAIN, 15));
        lbthongbao.setHorizontalAlignment(SwingConstants.CENTER);


        String[] columnJT = {"STT", "MAVT", "TENVT", "SO LUONG TON", "DON VI TINH"};
        Object[][] dataJT = {};
        model = new DefaultTableModel(dataJT, columnJT);
        tbvattu = new JTable(model);

        String sql = "SELECT * FROM VATTU";
        try {
            PreparedStatement pre = ConnectSQL.getCon().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            int stt = 1;
            while (rs.next()) {
                model.addRow(new Object[]{stt, rs.getInt("MaVT"), rs.getString("TenVT"), rs.getString("SoLuongTon"), rs.getString("DonViTinh")});
                stt ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tbvattu.setFillsViewportHeight(true);
        tbvattu.setBackground(new Color(248, 249, 250));
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
        tbvattu.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbvattu.setRowHeight(30);
        tbvattu.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16)); // Set font Header
        tbvattu.getTableHeader().setPreferredSize(new Dimension(100, 40)); // Set height
        tbvattu.getTableHeader().setBackground(new Color(247, 249, 252));
        tbvattu.getTableHeader().setForeground(new Color(105, 121, 141));
        tbvattu.setSelectionBackground(Color.YELLOW);
        tbvattu.setBackground(new Color(255, 255, 255));
        tbvattu.setForeground(new Color(84, 101, 121));
        tbvattu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JScrollPane JS = new JScrollPane(tbvattu);
        JS.setPreferredSize(new Dimension(1, 100));
        JS.setBounds(0, 300, 970, 350); // w: 700 bg: 100


        tbvattu.setFocusable(false); // Không tạo focus 1 phần tử
        tbvattu.setDefaultEditor(Object.class, null); // Không cho edit table
        tbvattu.getColumnModel().getColumn(0).setMaxWidth(70);
        tbvattu.getColumnModel().getColumn(0).setPreferredWidth(70);

        // Get row and value click
        tbvattu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (tbvattu.getSelectedRow() > -1) {
                    System.out.println(tbvattu.getValueAt(tbvattu.getSelectedRow(), 1).toString());
                    maVTValueSelected = tbvattu.getValueAt(tbvattu.getSelectedRow(), 1).toString();
                    tenVTValueSelected = tbvattu.getValueAt(tbvattu.getSelectedRow(), 2).toString();
                    soLuongSelected = tbvattu.getValueAt(tbvattu.getSelectedRow(), 3).toString();
                    donViValueSelected = tbvattu.getValueAt(tbvattu.getSelectedRow(), 4).toString();
                    rowSelected = tbvattu.getSelectedRow();
                    lbthongbao.setText("Bạn đang chọn vật tư: "+maVTValueSelected+" _ "+tenVTValueSelected);
                    System.out.println("Xóa row: " + rowSelected);
                }
            }
        });

        txtmavt.addKeyListener(this);
        txtmavt.addMouseListener(this);
        txtsoluongton.addKeyListener(this);

        btnaddVT = new JLabel("THÊM VẬT TƯ");
        btnaddVT.setBounds(150, 200, 140, 40);
        btnaddVT.setBackground(new Color(22, 114, 236));
        btnaddVT.setOpaque(true);
        btnaddVT.setForeground(Color.WHITE);
        btnaddVT.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon add_icon = new ImageIcon(JPanelVatTu.class.getResource("add.png"));
        Image image_add = add_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        add_icon.setImage(image_add);
        btnaddVT.setIcon(add_icon);
        btnaddVT.setIconTextGap(10);

        btneditVT = new JLabel("CHỈNH SỬA");
        btneditVT.setBounds(300, 200, 140, 40);
        btneditVT.setBackground(new Color(42, 185, 48));
        btneditVT.setOpaque(true);
        btneditVT.setForeground(Color.WHITE);
        btneditVT.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon edit_icon = new ImageIcon(JPanelVatTu.class.getResource("edit.png"));
        Image image_edit = edit_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        edit_icon.setImage(image_edit);
        btneditVT.setIcon(edit_icon);
        btneditVT.setIconTextGap(10);


        btnremoveVT = new JLabel("XÓA VẬT TƯ");
        btnremoveVT.setBounds(450, 200, 140, 40);
        btnremoveVT.setBackground(new Color(237, 149, 38));
        btnremoveVT.setOpaque(true);
        btnremoveVT.setForeground(Color.WHITE);
        btnremoveVT.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon remove_icon = new ImageIcon(JPanelVatTu.class.getResource("remove.png"));
        Image image_remove = remove_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        remove_icon.setImage(image_remove);
        btnremoveVT.setIcon(remove_icon);
        btnremoveVT.setIconTextGap(10);

        btnresetVT = new JLabel("RESET INPUT");
        btnresetVT.setBounds(600, 200, 140, 40);
        btnresetVT.setBackground(new Color(154, 15, 191));
        btnresetVT.setOpaque(true);
        btnresetVT.setForeground(Color.WHITE);
        btnresetVT.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon reset_icon = new ImageIcon(JPanelVatTu.class.getResource("reset.png"));
        Image image_reset = reset_icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        reset_icon.setImage(image_reset);
        btnresetVT.setIcon(reset_icon);
        btnresetVT.setIconTextGap(10);


        topContainer.add(btnaddVT);
        topContainer.add(btneditVT);
        topContainer.add(btnremoveVT);
        topContainer.add(btnresetVT);
        btnaddVT.addMouseListener(this);
        btneditVT.addMouseListener(this);
        btnremoveVT.addMouseListener(this);
        btnresetVT.addMouseListener(this);
        btnaddVT.addMouseListener(new JPanelHoaDon());
        System.out.println("CODE = " + btnaddVT.hashCode());

        txttenvt.addKeyListener(this);

        txtdonvi.addKeyListener(this);

        btnaddVT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btneditVT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnresetVT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnremoveVT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        topContainer.add(lbmavt);
        topContainer.add(lbdonvi);
        topContainer.add(lbsoluongton);
        topContainer.add(lbtenvt);
        topContainer.add(txtdonvi);
        topContainer.add(txtmavt);
        topContainer.add(txtsoluongton);
        topContainer.add(txttenvt);
        this.add(topContainer);
        this.add(lbthongbao);
        this.add(JS);

    }

    public String ChuanHoa(String s) {
        s = s.trim();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == ' ' && s.charAt(i + 1) == ' ') {
                s = s.substring(0, i) + s.substring(i + 1);
                i--;
            }
        }
        if (s.length()>0){
            s=String.valueOf(s.charAt(0)).toUpperCase()+s.substring(1);
        }
        return s;
    }

    public static void upDateList() {
        model.setRowCount(0);
        String sql = "SELECT * FROM VATTU";
        try {
            PreparedStatement pre = ConnectSQL.getCon().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            int stt = 1;
            while (rs.next()) {
                model.addRow(new Object[]{stt, rs.getInt("MaVT"), rs.getString("TenVT"), rs.getString("SoLuongTon"), rs.getString("DonViTinh")});
                stt ++;
            }
            System.out.println("LOAD THÀNH CÔNG VẬT TƯ");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR LOAD");
        }
    }

    public void addVT() {
        txtdonvi.setText(ChuanHoa(txtdonvi.getText()));
        txttenvt.setText(ChuanHoa(txttenvt.getText()));
        if (txtmavt.getText().equals("") || txtdonvi.getText().equals("") || txtsoluongton.getText().equals("")
                || txttenvt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin vật tư");
            return;
        }else{
            if ((txttenvt.getText().charAt(0)>='0' && txttenvt.getText().charAt(0) <= '9') ||
                    (txtdonvi.getText().charAt(0) >= '0' && txtdonvi.getText().charAt(0) <= '9')){
                System.out.println(txttenvt.getText());
                System.out.println(txtdonvi.getText());
                JOptionPane.showMessageDialog(null,"Tên vật tư và đơn vị tính không được bắt đầu bằng số !");
                return;
            }
        }
        String sql = "INSERT INTO VATTU VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pre = ConnectSQL.getCon().prepareStatement(sql);
            pre.setInt(1, Integer.parseInt(txtmavt.getText()));
            pre.setString(2, txttenvt.getText());
            pre.setInt(3, Integer.parseInt(txtsoluongton.getText()));
            pre.setString(4, txtdonvi.getText());
            pre.executeUpdate();
            model.addRow(new Object[]{tbvattu.getRowCount() + 1, Integer.parseInt(txtmavt.getText()),
                    txttenvt.getText(), Integer.parseInt(txtsoluongton.getText()), txtdonvi.getText()});
            JPanelHoaDon.getModelVatTu().addRow(new Object[]{Integer.parseInt(txtmavt.getText()),
                    txttenvt.getText(), Integer.parseInt(txtsoluongton.getText()), txtdonvi.getText()});

            JOptionPane.showMessageDialog(null, "Thêm vật tư thành công");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Mã vật tư đã tồn tại");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == txtmavt) {
            if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && txtmavt.getText().length()<9) || e.getKeyCode() == 8 || e.getKeyCode()==127) {
                txtmavt.setEditable(true);
            } else {
                txtmavt.setEditable(false);
            }
        }

        if (e.getSource() == txttenvt) {
            System.out.println("---");
            System.out.println(e.getKeyCode());
            if ((((e.getKeyCode()>=65 && e.getKeyCode()<=90) || e.getKeyCode()==0
                    || (e.getKeyChar()>='0' && e.getKeyChar()<='9' && txttenvt.getText().length()>0) || e.getKeyCode()==32  || e.getKeyCode() == 16) && txttenvt.getText().length()<45) || e.getKeyCode()==8 || e.getKeyCode() ==127  ){
                txttenvt.setEditable(true);
            }else txttenvt.setEditable(false);
        }

        if (e.getSource() == txtsoluongton){

            if ((((e.getKeyChar()>='1' && e.getKeyChar()<='9') || (e.getKeyChar()=='0' && txtsoluongton.getText().length()>0)) && txtsoluongton.getText().length()<9) || e.getKeyCode()==8 || e.getKeyCode()==127)
                txtsoluongton.setEditable(true);
            else txtsoluongton.setEditable(false);
        }

        if (e.getSource() == txtdonvi){
            if ((((e.getKeyCode()>=65 && e.getKeyCode()<=90) || e.getKeyCode()==0
                    || (e.getKeyChar()>='0' && e.getKeyChar()<='9' && txtdonvi.getText().length()>0 ) || e.getKeyCode()==32  || e.getKeyCode() == 16) && txtdonvi.getText().length()<45) || e.getKeyCode()==8 || e.getKeyCode() ==127  ){
                txtdonvi.setEditable(true);
            }
            else txtdonvi.setEditable(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        if(e.getSource() == txtsoluongton){
//            System.out.println("SLT = "+txtsoluongton.getText());
//        }
//        if(e.getSource() == txtmavt){
//            System.out.println("MVT ="+txtmavt.getText());
//        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == btnaddVT) {
            addVT();

        }
        if (e.getSource() == btneditVT) {
            System.out.println("Chỉnh sửa vật tư: " + maVTValueSelected);
            if(maVTValueSelected == null){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một vật tư để chỉnh sửa");
            }
            else {
                if(danglapHD_VT == true){
                    JOptionPane.showMessageDialog(null, "Vui lòng hoàn thành hóa đơn trước " +
                            "đó sau đó quay lại chỉnh sửa vật tư");
                    return;
                }
                new EditVatTu(maVTValueSelected, tenVTValueSelected, donViValueSelected, soLuongSelected);
            }
        }

        if (e.getSource() == btnresetVT) {
            String query = "SELECT * FROM VATTU AS VT ORDER BY VT.TENVT";
            try {
                System.out.println(query);
                model.setRowCount(0);
                PreparedStatement pre = ConnectSQL.getCon().prepareStatement(query);
                ResultSet rs = pre.executeQuery();
                int stt = 1;
                while (rs.next()) {
                    model.addRow(new Object[]{stt, rs.getInt("MaVT"), rs.getString("TenVT"), rs.getString("SoLuongTon"), rs.getString("DonViTinh")});
                    stt ++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            txtsoluongton.setText("");
            txttenvt.setText("");
            txtdonvi.setText("");
            txtmavt.setText("");
            lbthongbao.setText("Bạn chưa chọn vật tư nào");
            maVTValueSelected = null;
        }


        if (e.getSource() == btnremoveVT) {
            System.out.println("Xóa Vật Tư: " + maVTValueSelected);
            if (maVTValueSelected == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một vật tư để xóa");
                return;
            }
            if(danglapHD_VT == true){
                JOptionPane.showMessageDialog(null, "Vui lòng hoàn thành hóa đơn trước " +
                        "đó sau đó quay lại xóa vật tư");
                return;
            }
            int choose = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa vật tư: " + maVTValueSelected);
            if (choose == 0) {
                String sql = "DELETE FROM VATTU WHERE " + "MAVT = " + maVTValueSelected;
                System.out.println("Sql = " + sql);
                try {
                    Statement stmt = ConnectSQL.getCon().createStatement();
                    stmt.executeUpdate(sql);
                    upDateList();
                    JOptionPane.showMessageDialog(null, "Xóa vật tư thành công");
                    maVTValueSelected = null;
                    JPanelHoaDon.setVatTuNameSelected(null);
                    JPanelHoaDon.setVatTuValueSelected(null);
                    JPanelHoaDon.setMavtchoosed("NULL");
                    JPanelHoaDon.upDateListVatTu();
                    lbthongbao.setText("Bạn chưa chọn vật tư nào");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Xóa vật tư không thành công");
                    ex.printStackTrace();
                }
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
//        if (e.getSource() == txtmavt) {
//            txtmavt.setEditable(true);
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == btnaddVT) {
            btnaddVT.setBackground(new Color(255, 209, 30));
        }
        if (e.getSource() == btneditVT) {
            btneditVT.setBackground(new Color(255, 209, 30));
        }
        if (e.getSource() == btnremoveVT) {
            btnremoveVT.setBackground(new Color(255, 209, 30));
        }
        if (e.getSource() == btnresetVT) {
            btnresetVT.setBackground(new Color(255, 209, 30));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        if (e.getSource() == txtmavt) {
//            txtmavt.setEditable(true);
//        }
        if (e.getSource() == btnaddVT) {
            btnaddVT.setBackground(new Color(22, 114, 236));
        }
        if (e.getSource() == btneditVT) {
            btneditVT.setBackground(new Color(42, 185, 48));
        }
        if (e.getSource() == btnremoveVT) {
            btnremoveVT.setBackground(new Color(237, 149, 38));
        }
        if (e.getSource() == btnresetVT) {
            btnresetVT.setBackground(new Color(154, 15, 191));
        }
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public static void setMaVTValueSelected(String maVTValueSelected) {
        JPanelVatTu.maVTValueSelected = maVTValueSelected;
    }

    public static void setThongBao(String thongbao) {
        JPanelVatTu.lbthongbao.setText(thongbao);
    }

    public static void setDanglapHD_VT(boolean danglapHD_VT) {
        JPanelVatTu.danglapHD_VT = danglapHD_VT;
    }

    public static void reSetAll(){
        lbthongbao.setText("Bạn chưa chọn vật tư nào");
        maVTValueSelected = null;
        upDateList();
    }

}
