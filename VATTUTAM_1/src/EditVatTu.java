import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditVatTu extends JFrame implements ActionListener, KeyListener {
    private final int WIDTH_EDIT = 500;
    private final int HEIGHT_EDIT = 450;
    private JPanel JPmain;
    private JLabel JLBTop;
    private JTextField txtMaVatTu;
    private JLabel JLBMaVatTu;
    private JTextField txtTenVatTu;
    private JLabel JLBTenVatTu;
    private JButton btnEdit;
    private JLabel JLBSoLuongTon;
    private JTextField txtSoLuongTon;
    private JLabel JLBDonViTinh;
    private JTextField txtDonViTinh;
    private JLabel JLBHappy;
    private Connection con;
    public EditVatTu(String MaVT, String TenVT, String DonViTinh, String SoLuongTon) {
        System.out.println("EDIT MÃ VẬT TƯ: " + MaVT);
        this.setTitle("CHỈNH SỬA VẬT TƯ");
        this.setSize(WIDTH_EDIT, HEIGHT_EDIT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.con = ConnectSQL.getCon();

        JPmain = new JPanel();
        JPmain.setBounds(0, 0, WIDTH_EDIT, HEIGHT_EDIT);
        JPmain.setLayout(null);
        JPmain.setBackground(new Color(45, 47, 49));

        JLBTop = new JLabel("CHỈNH SỬA VẬT TƯ");
        JLBTop.setBackground(new Color(33, 168, 109));
        JLBTop.setOpaque(true);
        JLBTop.setSize(WIDTH_EDIT, 70); // height 70
        JLBTop.setHorizontalAlignment(SwingConstants.CENTER);
        JLBTop.setFont(new Font("Ubuntu", Font.BOLD, 20)); //Helvetica
        JLBTop.setForeground(Color.WHITE);
        JPmain.add(JLBTop);

        JLBMaVatTu = new JLabel("MÃ VẬT TƯ: ");
        JLBMaVatTu.setBounds(30, 110, 140, 40); // height 120
        JLBMaVatTu.setBackground(new Color(36, 169, 111));
        JLBMaVatTu.setForeground(Color.WHITE);
        JLBMaVatTu.setHorizontalAlignment(SwingConstants.CENTER);
        JLBMaVatTu.setOpaque(true);

        JLBTenVatTu = new JLabel("TÊN VẬT TƯ: ");
        JLBTenVatTu.setBounds(30, 160, 140, 40); // height = 160
        JLBTenVatTu.setBackground(new Color(102, 195, 239));
        JLBTenVatTu.setForeground(Color.WHITE);
        JLBTenVatTu.setOpaque(true);

        JLBTenVatTu.setHorizontalAlignment(SwingConstants.CENTER);
        JLBDonViTinh = new JLabel("ĐƠN VỊ TÍNH: ");
        JLBDonViTinh.setBounds(30, 210, 140, 40); // height 120
        JLBDonViTinh.setBackground(new Color(36, 169, 111));
        JLBDonViTinh.setForeground(Color.WHITE);
        JLBDonViTinh.setHorizontalAlignment(SwingConstants.CENTER);
        JLBDonViTinh.setOpaque(true);

        JLBSoLuongTon = new JLabel("SỐ LƯỢNG TỒN:");
        JLBSoLuongTon.setBounds(30, 260, 140, 40); // height 120
        JLBSoLuongTon.setBackground(new Color(36, 169, 111));
        JLBSoLuongTon.setForeground(Color.WHITE);
        JLBSoLuongTon.setHorizontalAlignment(SwingConstants.CENTER);
        JLBSoLuongTon.setOpaque(true);

        JPmain.add(JLBMaVatTu);
        JPmain.add(JLBTenVatTu);
        JPmain.add(JLBDonViTinh);
        JPmain.add(JLBSoLuongTon);

        txtMaVatTu = new JTextField();
        txtMaVatTu.setBounds(190, 110, 220, 40); // 150, 50
        txtMaVatTu.setBackground(new Color(66, 66, 66));
        txtMaVatTu.setForeground(new Color(214, 215, 217));
        txtMaVatTu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtMaVatTu.setFont(new Font("Arial", Font.BOLD, 15));
        txtMaVatTu.setForeground(Color.RED);
        JPmain.add(txtMaVatTu);

        txtTenVatTu = new JTextField();
        txtTenVatTu.setBounds(190, 160, 220, 40); // 160
        txtTenVatTu.setBackground(new Color(66, 66, 66));
        txtTenVatTu.setForeground(new Color(214, 215, 217));
        txtTenVatTu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtTenVatTu.setFont(new Font("Arial", Font.BOLD, 15));
        JPmain.add(txtTenVatTu);

        txtDonViTinh = new JTextField();
        txtDonViTinh.setBounds(190, 210, 220, 40); // 160
        txtDonViTinh.setBackground(new Color(66, 66, 66));
        txtDonViTinh.setForeground(new Color(214, 215, 217));
        txtDonViTinh.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtDonViTinh.setFont(new Font("Arial", Font.BOLD, 15));
        JPmain.add(txtDonViTinh);

        txtSoLuongTon = new JTextField();
        txtSoLuongTon.setBounds(190, 260, 220, 40); // 160
        txtSoLuongTon.setBackground(new Color(66, 66, 66));
        txtSoLuongTon.setForeground(new Color(214, 215, 217));
        txtSoLuongTon.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtSoLuongTon.setFont(new Font("Arial", Font.BOLD, 15));
        txtSoLuongTon.setForeground(Color.RED);
        JPmain.add(txtSoLuongTon);

        btnEdit = new JButton("EDIT & LƯU");
        btnEdit.setBounds((WIDTH_EDIT - 100) / 2, 330, 100, 40); // 220
        btnEdit.setBackground(new Color(96, 100, 191));
        btnEdit.setBorder(null);
        btnEdit.setFocusable(false);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.addActionListener(this);
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPmain.add(btnEdit);

        JLBHappy = new JLabel("Chào ngày mới vui vẻ");
        JLBHappy.setBounds((HEIGHT_EDIT - 300) / 2, 260, 200, 30);
        JLBHappy.setHorizontalAlignment(SwingConstants.CENTER);
        JPmain.add(JLBHappy);


        txtTenVatTu.setText(TenVT);
        txtTenVatTu.addKeyListener(this);
        txtMaVatTu.setText(MaVT);
        txtSoLuongTon.setText(SoLuongTon);
        txtDonViTinh.setText(DonViTinh);
        txtDonViTinh.addKeyListener(this);
        txtMaVatTu.setEditable(false);
        txtSoLuongTon.setEditable(false);


        this.add(JPmain);
        this.addKeyListener(this);
        txtMaVatTu.addKeyListener(this);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        txtTenVatTu.setText(JPanelVatTu.ChuanHoa(txtTenVatTu.getText()));
        txtDonViTinh.setText(JPanelVatTu.ChuanHoa(txtDonViTinh.getText()));
        if (txtTenVatTu.getText().equals("") || txtDonViTinh.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
        } else if ((txtTenVatTu.getText().charAt(0)>='0' && txtTenVatTu.getText().charAt(0) <= '9') ||
                (txtDonViTinh.getText().charAt(0) >= '0' && txtDonViTinh.getText().charAt(0) <= '9')){
            JOptionPane.showMessageDialog(null,"Tên vật tư và đơn vị tính không được bắt đầu bằng số !");
        }else
        {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc chỉnh sửa?");
            if(result == 0){
                try {
                    Statement stmt = con.createStatement();
                    String sql = "UPDATE VATTU " +
                            "SET TenVT = N'"+txtTenVatTu.getText()+ "', DONVITINH = N'"+txtDonViTinh.getText()+
                            "' WHERE MAVT = "+Integer.parseInt(txtMaVatTu.getText());
                    stmt.executeUpdate(sql);
                    stmt.close();
                    JPanelVatTu.upDateList();
                    JPanelHoaDon.upDateListVatTu();
                    JPanelVatTu.setMaVTValueSelected(null);
                    JPanelVatTu.setThongBao("Bạn chưa chọn vật tư nào");
                    JPanelHoaDon.setVatTuNameSelected(null);
                    JPanelHoaDon.setVatTuValueSelected(null);
                    JPanelHoaDon.setMavtchoosed("NULL");
                    JOptionPane.showMessageDialog(null, "Chỉnh sửa vật tư thành công");
                    this.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Chỉnh sửa vật tư thất bại");
                }
            }

        };
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == txtTenVatTu) {
            if ((((e.getKeyCode()>=65 && e.getKeyCode()<=90) || e.getKeyCode()==0
                    || (e.getKeyChar()>='0' && e.getKeyChar()<='9' && txtTenVatTu.getText().length()>0) || e.getKeyCode()==32  || e.getKeyCode() == 16) && txtTenVatTu.getText().length()<45) || e.getKeyCode()==8 || e.getKeyCode() ==127  ){
                txtTenVatTu.setEditable(true);
            }else txtTenVatTu.setEditable(false);
        }

        if (e.getSource() == txtDonViTinh){
            if ((((e.getKeyCode()>=65 && e.getKeyCode()<=90) || e.getKeyCode()==0
                    || (e.getKeyChar()>='0' && e.getKeyChar()<='9' && txtDonViTinh.getText().length()>0 ) || e.getKeyCode()==32  || e.getKeyCode() == 16) && txtDonViTinh.getText().length()<45) || e.getKeyCode()==8 || e.getKeyCode() ==127  ){
                txtDonViTinh.setEditable(true);
            }
            else txtDonViTinh.setEditable(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

class TESTMAINNHE {
    public static void main(String[] args) {
        new EditVatTu("1000", "Xi Măng", "Bao", "2000");
    }
}

