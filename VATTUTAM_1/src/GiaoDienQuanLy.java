import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

// SQL Sai kiểu dữ liệu
// Xóa thì cho null
public class GiaoDienQuanLy extends JFrame implements MouseListener {
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
    private JLabel lb04; // name tab1
    private JLabel lb05; // name tab2
    private JLabel lb06; // name tab3
    private JLabel lb07; // name tab4
    private JTabbedPane tbp01;
    private static Connection con;

    GiaoDienQuanLy() {
        this.setTitle("Giao Diện Quản Lý");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.con = new ConnectSQL().getCon();

        pn00 = new JPanel();
        pn00.setBounds(0, 0, WIDTH, 50);
        pn00.setBackground(new Color(255, 85, 85));
        lb00 = new JLabel("QUẢN LÝ VẬT TƯ");
        lb00.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        lb00.setForeground(Color.WHITE);
        pn00.add(lb00);

        JLabel lb001 = new JLabel("XIN CHÀO, "+Login.getUserName());
        lb001.setForeground(new Color(255, 85, 85));
        lb001.setBounds(20, 20, 210, 100);
        lb001.setBackground(new Color(255, 209, 30));
        lb001.setOpaque(true);
        ImageIcon icon00 = new ImageIcon(GiaoDienQuanLy.class.getResource("man.png"));
        Image newimg00 = icon00.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        lb001.setHorizontalAlignment(JLabel.CENTER);
        lb001.setHorizontalTextPosition(JLabel.CENTER);
        lb001.setVerticalTextPosition(JLabel.BOTTOM);
        icon00.setImage(newimg00);
        lb001.setIcon(icon00);
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
        lb01.setIcon(icon01);
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
        lb02.setIcon(icon02);
        lb02.setIconTextGap(10);
        pn04.add(lb02);


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
        lb03.setIcon(icon03);
        lb03.setIconTextGap(10);
        pn05.add(lb03);


        pn031 = new JPanel();
        pn031.setBounds(20, 410, 210, 60);
        pn031.setBackground(Color.magenta);
        pn031.setLayout(null);
        lb031 = new JLabel("QUẢN LÝ THỐNG KÊ");
        lb031.setSize(210, 60);
        lb031.setBackground(new Color(33, 205, 65));
//        lb031.setBackground(new Color(17, 182, 202));
        lb031.setOpaque(true);
        lb031.setHorizontalAlignment(SwingConstants.CENTER);
        lb031.setForeground(Color.WHITE);

        ImageIcon icon04 = new ImageIcon(GiaoDienQuanLy.class.getResource("overview.png"));
        Image newimg04 = icon04.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        icon04.setImage(newimg04);
        lb031.setIcon(icon04);
        lb031.setIconTextGap(10);
        pn031.add(lb031);


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
        pn01.add(pn05);
        pn01.add(pn031);
        this.add(pn02);
        pn02.add(tbp01);

        lb01.addMouseListener(this);
        lb02.addMouseListener(this);
        lb03.addMouseListener(this);
        lb031.addMouseListener(this);


        this.setVisible(true);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == lb01) {
            tbp01.setSelectedIndex(0);
        } else if (e.getSource() == lb02) {
            tbp01.setSelectedIndex(1);
        } else if (e.getSource() == lb03) {
            tbp01.setSelectedIndex(2);
        } else if (e.getSource() == lb031) {
            tbp01.setSelectedIndex(3);
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
        }
    }

}

class TESTPRO {
    public static void main(String[] args) {
        new GiaoDienQuanLy();
    }
}
