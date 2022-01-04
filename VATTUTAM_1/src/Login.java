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
    private JLabel JLBHappy;
    private String txt = "";
    private static String userName;
    private static Connection con;

    public Login() {
        this.setTitle("Đăng nhập hệ thống");
        this.setSize(WIDTH_LOGIN, HEIGHT_LOGIN);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.con = new ConnectSQL().getCon();

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

        JLBHappy = new JLabel("Chào ngày mới vui vẻ");
        JLBHappy.setBounds((WIDTH_LOGIN - 200) / 2, 260, 200, 30);
        JLBHappy.setHorizontalAlignment(SwingConstants.CENTER);
        JPmain.add(JLBHappy);

        this.add(JPmain);
        this.addKeyListener(this);
        txtUsername.addKeyListener(this);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sql = "SELECT * FROM USERS WHERE USERS.USERNAME = '"+txtUsername.getText()+"'";
        System.out.println("SQL ="+sql);
        boolean temp=false;
        try {
            PreparedStatement pre = ConnectSQL.getCon().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                if(rs.getString("USERNAME").equals(txtUsername.getText()) && rs.getString("PASS").equals(txtPassword.getText())){
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                    temp = true;
                    this.userName = txtUsername.getText();
                    this.dispose();
                    new GiaoDienQuanLy();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (!temp)
            JOptionPane.showMessageDialog(null, "Sai thông tin đăng nhập...");



//        if (txtPassword.getText().equals("123456") && txtUsername.getText().equals("admin")) {
//            JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
//            new GiaoDienQuanLy();
//            this.dispose();
//        } else JOptionPane.showMessageDialog(null, "Sai thông tin đăng nhập...");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if(e.getSource() == txtUsername){
//            if(e.getKeyCode() > 65 && e.getKeyCode() < 90) {
//                txt += e.getKeyChar();
//            }
//            txtUsername.setText(txt);
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        new Login();
    }

    public static String getUserName() {
        return userName;
    }
}
