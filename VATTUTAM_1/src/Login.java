import DoiTuong.NhanVien;
import DoiTuong.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DoiTuong.User;
public class Login extends JFrame implements ActionListener, KeyListener {
    private final int WIDTH_LOGIN = 400;
    private final int HEIGHT_LOGIN = 340;
    private JPanel JPmain;
    private JLabel JLBTop;
    private JTextField txtUsername;
    private JLabel JLBUsername;
    private JPasswordField txtPassword;
    private JLabel JLBPassword;
    private JButton btnLogin;
    private static String userName;
    private static int quyenHanh = -1;
    private static String MaNV;
    private static String HoNV;
    private static String TenNV;
    private static String PhaiNV;
    private ArrayList<User> userArrayList;

    private static Connection con;

    public Login() {
        this.setTitle("Đăng nhập hệ thống");
        this.setSize(WIDTH_LOGIN, HEIGHT_LOGIN);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.con = new ConnectSQL().getCon();

        userArrayList = new ArrayList<>();
        loadDataUser();
        JPmain = new JPanel();
        JPmain.setBounds(0, 0, WIDTH_LOGIN, HEIGHT_LOGIN);
        JPmain.setLayout(null);
        JPmain.setBackground(new Color(45, 47, 49));

        JLBTop = new JLabel("Vui Lòng Đăng Nhập");
        JLBTop.setBackground(new Color(33, 168, 109));
        JLBTop.setOpaque(true);
        JLBTop.setSize(WIDTH_LOGIN, 70); // height 70
        JLBTop.setHorizontalAlignment(SwingConstants.CENTER);
        JLBTop.setFont(new Font("Ubuntu", Font.BOLD, 20)); //Helvetica
        JLBTop.setForeground(Color.WHITE);
        JPmain.add(JLBTop);

        JLBUsername = new JLabel("username: ");
        JLBUsername.setBounds(30, 110, 100, 40); // height 120
        JLBUsername.setBackground(new Color(36, 169, 111));
        JLBUsername.setForeground(Color.WHITE);
        JLBUsername.setHorizontalAlignment(SwingConstants.CENTER);
        JLBUsername.setOpaque(true);
        JLBPassword = new JLabel("password: ");
        JLBPassword.setBounds(30, 160, 100, 40); // height = 160
        JLBPassword.setBackground(new Color(102, 195, 239));
        JLBPassword.setForeground(Color.WHITE);
        JLBPassword.setOpaque(true);
        JLBPassword.setHorizontalAlignment(SwingConstants.CENTER);
        JPmain.add(JLBUsername);
        JPmain.add(JLBPassword);

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

        btnLogin = new JButton("Đăng Nhập");
        btnLogin.setBounds((WIDTH_LOGIN - 100) / 2, 220, 100, 40); // 220
        btnLogin.setBackground(new Color(96, 100, 191));
        btnLogin.setBorder(null);
        btnLogin.setFocusable(false);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(this);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPmain.add(btnLogin);

        this.add(JPmain);
        txtPassword.addKeyListener(this);
        txtUsername.addKeyListener(this);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean checkDN = false;
        for (User user: userArrayList){
            if (user.getUserName().equals(txtUsername.getText()) && user.getPassWord().equals(String.valueOf(txtPassword.getPassword()))){
                this.userName = user.getUserName();
                this.quyenHanh = user.getQuyen();
                MaNV = user.getUserName();
                checkDN = true;
                break;
            }
        }
        if (checkDN == false){
            JOptionPane.showMessageDialog(null,"Tài khoản hoặc mật khẩu không đúng!");
        }
        else{
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
            this.dispose();
            new GiaoDienQuanLy();
            ArrayList<NhanVien> nhanVienArrayList= GiaoDienQuanLy.getNhanVienArrayList();
            for (NhanVien nhanVien:nhanVienArrayList){
                if (nhanVien.getMaNhanVien().equals(MaNV)){
                    HoNV = nhanVien.getHoNhanVien();
                    TenNV = nhanVien.getTenNhanVien();
                    PhaiNV = nhanVien.getPhaiNhanVien();
                    break;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static String getUserName() {
        return userName;
    }

    public static int getQuyenHanh() {
        return quyenHanh;
    }

    public static String[] ObjNhanVien (){
        return new String[] {String.valueOf(MaNV), HoNV, TenNV, PhaiNV};
    }

    public static String getMaNV() {
        return MaNV;
    }

    public static String getTenNV() {
        return HoNV +" " + TenNV;
    }
    private void loadDataUser(){
        String query = "SELECT * FROM USERS";
        try {
            PreparedStatement pre = ConnectSQL.getCon().prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            while (rs.next())
                userArrayList.add(new User(rs.getString("UserName"),rs.getString("PASS"),rs.getInt("QUYEN")));
            pre.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new Login();
    }
}
