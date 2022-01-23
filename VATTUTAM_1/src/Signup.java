import DoiTuong.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener, KeyListener {
    private final int WIDTH_LOGIN = 400;
    private final int HEIGHT_LOGIN = 450;
    private JPanel JPmain;
    private JLabel JLBTop;
    private JTextField txtUsername;
    private JLabel JLBUsername;
    private JPasswordField txtPassword;
    private JLabel JLBPassword;
    private JButton btnSignup;
    private static String userName;
    private static Connection con;
    private static JLabel JLBchonNV;
    private static JLabel JLBchonQuyen;
    private JComboBox boxMaNV;
    private JComboBox boxQuyenHanh;
    private static boolean checkSignup = false;

    public Signup(){
        checkSignup = true;
        this.setTitle("Đăng Ký tài khoản");
        this.setSize(WIDTH_LOGIN, HEIGHT_LOGIN);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.con = new ConnectSQL().getCon();

        JPmain = new JPanel();
        JPmain.setBounds(0, 0, WIDTH_LOGIN, HEIGHT_LOGIN);
        JPmain.setLayout(null);
        JPmain.setBackground(new Color(255, 255, 255));

        JLBTop = new JLabel("Đăng ký tài khoản");
        JLBTop.setBackground(new Color(33, 168, 109));
        JLBTop.setOpaque(true);
        JLBTop.setSize(WIDTH_LOGIN, 70); // height 70
        JLBTop.setHorizontalAlignment(SwingConstants.CENTER);
        JLBTop.setFont(new Font("Ubuntu", Font.BOLD, 20)); //Helvetica
        JLBTop.setForeground(Color.WHITE);
        JPmain.add(JLBTop);

        JLBUsername = new JLabel("Tên TK: ");
        JLBUsername.setBounds(30, 110, 100, 40); // height 120
        JLBUsername.setBackground(new Color(36, 169, 111));
        JLBUsername.setForeground(Color.WHITE);
        JLBUsername.setHorizontalAlignment(SwingConstants.CENTER);
        JLBUsername.setOpaque(true);

        JLBPassword = new JLabel("Mật Khẩu: ");
        JLBPassword.setBounds(30, 160, 100, 40); // height = 160
        JLBPassword.setBackground(new Color(102, 195, 239));
        JLBPassword.setForeground(Color.WHITE);
        JLBPassword.setOpaque(true);
        JLBPassword.setHorizontalAlignment(SwingConstants.CENTER);

        JLBchonNV = new JLabel("Chọn MaNV: ");
        JLBchonNV.setBounds(30, 210, 100, 40); // height = 160
        JLBchonNV.setBackground(new Color(36, 169, 111));
        JLBchonNV.setForeground(Color.WHITE);
        JLBchonNV.setOpaque(true);
        JLBchonNV.setHorizontalAlignment(SwingConstants.CENTER);

        JLBchonQuyen = new JLabel("Chọn Quyền: ");
        JLBchonQuyen.setBounds(30, 260, 100, 40); // height = 160
        JLBchonQuyen.setBackground(new Color(36, 169, 111));
        JLBchonQuyen.setForeground(Color.WHITE);
        JLBchonQuyen.setOpaque(true);
        JLBchonQuyen.setHorizontalAlignment(SwingConstants.CENTER);


        JPmain.add(JLBUsername);
        JPmain.add(JLBPassword);
        JPmain.add(JLBchonNV);
        JPmain.add(JLBchonQuyen);




        txtUsername = new JTextField();
        txtUsername.setBounds(150, 110, 200, 40); // 150, 50
        txtUsername.setBackground(new Color(66, 66, 66));
        txtUsername.setForeground(new Color(214, 215, 217));
        txtUsername.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtUsername.setFont(new Font("Arial", Font.BOLD, 15));
        JPmain.add(txtUsername);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 160, 200, 40); // 160
        txtPassword.setBackground(new Color(66, 66, 66));
        txtPassword.setForeground(new Color(214, 215, 217));
        txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtPassword.setFont(new Font("Arial", Font.BOLD, 20));
        JPmain.add(txtPassword);


        boxMaNV = new JComboBox();
        String sql = "SELECT * FROM NHANVIEN";
        try {
            PreparedStatement pre = ConnectSQL.getCon().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                boxMaNV.addItem(rs.getInt("MaNV"));
            }
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boxMaNV.setBounds(150, 210, 200, 40);
        boxMaNV.setBackground(new Color(255, 66, 66));
        boxMaNV.setForeground(new Color(255, 255, 255));
        boxMaNV.setBorder(BorderFactory.createEmptyBorder());
        System.out.println(boxMaNV.getSelectedIndex()); // -1
        System.out.println(boxMaNV.getItemAt(boxMaNV.getSelectedIndex())); // value String
        boxMaNV.addActionListener(this);
        JPmain.add(boxMaNV);



        boxQuyenHanh = new JComboBox();
        boxQuyenHanh.addItem("Quản Lý");
        boxQuyenHanh.addItem("Nhân Viên");

        boxQuyenHanh.setBounds(150, 260, 200, 40);
        boxQuyenHanh.setBackground(new Color(255, 66, 66));
        boxQuyenHanh.setForeground(new Color(255, 255, 255));
        boxQuyenHanh.setBorder(BorderFactory.createEmptyBorder());

        boxQuyenHanh.setSelectedIndex(-1);
        boxMaNV.setSelectedIndex(-1);
//        System.out.println(boxQuyenHanh.getSelectedIndex()); // -1
//        System.out.println(boxQuyenHanh.getItemAt(boxQuyenHanh.getSelectedIndex())); // value String
        txtUsername.setText("");
        boxQuyenHanh.addActionListener(this);
        JPmain.add(boxQuyenHanh);



        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                checkSignup = false;
            }
        });
        btnSignup = new JButton("Đăng Ký");
        btnSignup.setBounds((WIDTH_LOGIN - 100) / 2, 350, 100, 40); // 220
        btnSignup.setBackground(new Color(96, 100, 191));
        btnSignup.setBorder(null);
        btnSignup.setFocusable(false);
        btnSignup.setForeground(Color.WHITE);
        btnSignup.addActionListener(this);
        btnSignup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPmain.add(btnSignup);

        this.add(JPmain);
        txtPassword.addKeyListener(this);
        txtUsername.addKeyListener(this);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boxMaNV) {
//            System.out.println(boxMaNV.getSelectedIndex()); // -1
//            System.out.println(boxMaNV.getItemAt(boxMaNV.getSelectedIndex())); // value String
//            System.out.println("Hí + "+(int) boxMaNV.getItemAt(boxMaNV.getSelectedIndex()));
            txtUsername.setText((String.valueOf(boxMaNV.getItemAt(boxMaNV.getSelectedIndex()))));
        }
        if (e.getSource() == btnSignup) {

            if (boxMaNV.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
                return;
            }

            if (txtPassword.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu");
                return;
            }

            if (boxQuyenHanh.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn quyền hành");
                return;
            }

            if (GiaoDienQuanLy.isUserTonTai(txtUsername.getText())) {
                int choose = JOptionPane.showConfirmDialog(null, "Nhân viên có mã số " + txtUsername.getText() + " đã có tài khoản, bạn muốn đổi mật khẩu và quyền hành?");
                if (choose == 0) {
                    int quyen=-1;
                    if (boxQuyenHanh.getSelectedIndex() == 0) quyen = 1;
                    else if (boxQuyenHanh.getSelectedIndex() == 1) quyen = 0;
                    GiaoDienQuanLy.changeUser(txtUsername.getText(), txtPassword.getText(),quyen);
                    JOptionPane.showMessageDialog(null, "Thay đổi thành công!");
                }
                return;
            } else {
                int quyen=-1;
                if (boxQuyenHanh.getSelectedIndex() == 0) quyen = 1;
                else if (boxQuyenHanh.getSelectedIndex() == 1) quyen = 0;
                GiaoDienQuanLy.addUser(new User(txtUsername.getText(), txtPassword.getText(), quyen));
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == txtUsername){
            txtUsername.setEditable(false);
        }
        if (e.getSource() == txtPassword){
            if (e.getKeyCode()==0){
                JOptionPane.showMessageDialog(null,"Vui lòng tắt bộ gõ tiếng Việt và nhập lại từ đầu!!!");
                txtPassword.setText("");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        new Signup();
    }

    public static String getUserName() {
        return userName;
    }
    public static boolean getCheckSignup(){
        return checkSignup;
    }
    public static void setCheckSignup(boolean checkS){
        checkSignup=checkS;
    }

}
