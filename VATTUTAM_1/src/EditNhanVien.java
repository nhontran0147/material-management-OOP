import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditNhanVien extends JDialog implements ActionListener, KeyListener {
    private final int WIDTH_EDIT = 500;
    private final int HEIGHT_EDIT = 450;
    private JPanel JPmain;
    private JLabel JLBTop;
    private JTextField txtMaNhanVien;
    private JLabel JLBMaNhanVien;
    private JTextField txtHoNhanVien;
    private JLabel JLBHoNhanVien;
    private JButton btnEdit;
    private JLabel JLBTenNhanVien;
    private JTextField txtTenNhanVien;
    private JLabel JLBPhaiNhanVien;
    private static String txtPhaiNhanVien = "";

    private JButton JBTPhaiNam;
    private JButton JBTPhaiNu;

    private JLabel JLBHappy;
    private Connection con;
    private static boolean checkEditNhanVien=false;
    public EditNhanVien(String MaNV, String HoNV, String TenNV, String Phai) {
        checkEditNhanVien= true;
        System.out.println("EDIT MÃ NHÂN VIÊN: " + MaNV);
        this.setTitle("CHỈNH SỬA NHÂN VIÊN");
        this.setSize(WIDTH_EDIT, HEIGHT_EDIT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.con = ConnectSQL.getCon();
        this.setModal(true);

        JPmain = new JPanel();
        JPmain.setBounds(0, 0, WIDTH_EDIT, HEIGHT_EDIT);
        JPmain.setLayout(null);
        JPmain.setBackground(new Color(45, 47, 49));

        JLBTop = new JLabel("CHỈNH SỬA NHÂN VIÊN");
        JLBTop.setBackground(new Color(33, 168, 109));
        JLBTop.setOpaque(true);
        JLBTop.setSize(WIDTH_EDIT, 70); // height 70
        JLBTop.setHorizontalAlignment(SwingConstants.CENTER);
        JLBTop.setFont(new Font("Ubuntu", Font.BOLD, 20)); //Helvetica
        JLBTop.setForeground(Color.WHITE);
        JPmain.add(JLBTop);

        JLBMaNhanVien = new JLabel("MÃ NHÂN VIÊN: ");
        JLBMaNhanVien.setBounds(30, 110, 140, 40); // height 120
        JLBMaNhanVien.setBackground(new Color(36, 169, 111));
        JLBMaNhanVien.setForeground(Color.WHITE);
        JLBMaNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        JLBMaNhanVien.setOpaque(true);

        JLBHoNhanVien = new JLabel("HỌ NHÂN VIÊN: ");
        JLBHoNhanVien.setBounds(30, 160, 140, 40); // height = 160
        JLBHoNhanVien.setBackground(new Color(102, 195, 239));
        JLBHoNhanVien.setForeground(Color.WHITE);
        JLBHoNhanVien.setOpaque(true);

        JLBHoNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        JLBTenNhanVien = new JLabel("TÊN NHÂN VIÊN: ");
        JLBTenNhanVien.setBounds(30, 210, 140, 40); // height 120
        JLBTenNhanVien.setBackground(new Color(36, 169, 111));
        JLBTenNhanVien.setForeground(Color.WHITE);
        JLBTenNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        JLBTenNhanVien.setOpaque(true);

        JLBPhaiNhanVien = new JLabel("PHÁI NHÂN VIÊN:");
        JLBPhaiNhanVien.setBounds(30, 260, 140, 40); // height 120
        JLBPhaiNhanVien.setBackground(new Color(36, 169, 111));
        JLBPhaiNhanVien.setForeground(Color.WHITE);
        JLBPhaiNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        JLBPhaiNhanVien.setOpaque(true);

        JPmain.add(JLBMaNhanVien);
        JPmain.add(JLBHoNhanVien);
        JPmain.add(JLBPhaiNhanVien);
        JPmain.add(JLBTenNhanVien);

        txtMaNhanVien = new JTextField();
        txtMaNhanVien.setBounds(190, 110, 220, 40); // 150, 50
        txtMaNhanVien.setBackground(new Color(66, 66, 66));
        txtMaNhanVien.setForeground(new Color(214, 215, 217));
        txtMaNhanVien.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtMaNhanVien.setFont(new Font("Arial", Font.BOLD, 15));
        txtMaNhanVien.setForeground(Color.RED);
        JPmain.add(txtMaNhanVien);

        txtHoNhanVien = new JTextField();
        txtHoNhanVien.setBounds(190, 160, 220, 40); // 160
        txtHoNhanVien.setBackground(new Color(66, 66, 66));
        txtHoNhanVien.setForeground(new Color(214, 215, 217));
        txtHoNhanVien.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtHoNhanVien.setFont(new Font("Arial", Font.BOLD, 15));
        txtHoNhanVien.addKeyListener(this);
        JPmain.add(txtHoNhanVien);

        txtTenNhanVien = new JTextField();
        txtTenNhanVien.setBounds(190, 210, 220, 40); // 160
        txtTenNhanVien.setBackground(new Color(66, 66, 66));
        txtTenNhanVien.setForeground(new Color(214, 215, 217));
        txtTenNhanVien.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtTenNhanVien.setFont(new Font("Arial", Font.BOLD, 15));
        txtTenNhanVien.addKeyListener(this);
        JPmain.add(txtTenNhanVien);

        JBTPhaiNam = new JButton("NAM");
        JBTPhaiNam.addActionListener(this);
        JBTPhaiNam.setBounds(190, 260, 100, 40); // 160
        JBTPhaiNam.setBackground(new Color(66, 66, 66));
        JBTPhaiNam.setForeground(new Color(214, 215, 217));
        JBTPhaiNam.setFocusable(false);
        JBTPhaiNam.setBorderPainted(false);
        JPmain.add(JBTPhaiNam);

        JBTPhaiNu = new JButton("NỮ");
        JBTPhaiNu.addActionListener(this);
        JBTPhaiNu.setBounds(310, 260, 100, 40); // 160
        JBTPhaiNu.setBackground(new Color(66, 66, 66));
        JBTPhaiNu.setForeground(new Color(214, 215, 217));
        JBTPhaiNu.setFocusable(false);
        JBTPhaiNu.setBorderPainted(false);
        JPmain.add(JBTPhaiNu);


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

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                checkEditNhanVien = false;
            }
        });

        txtHoNhanVien.setText(HoNV);
        txtMaNhanVien.setText(MaNV);
        txtTenNhanVien.setText(TenNV);
        if(Phai.equals("Nam")) {
            txtPhaiNhanVien = "Nam";
            JBTPhaiNam.setBackground(new Color(42, 185, 48));
        }
        else if(Phai.equals("Nữ")){
            txtPhaiNhanVien = "Nữ";
            JBTPhaiNu.setBackground(new Color(42, 185, 48));
        }
        txtMaNhanVien.setEditable(false);

        this.add(JPmain);
        this.addKeyListener(this);
        txtMaNhanVien.addKeyListener(this);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnEdit){
            txtHoNhanVien.setText(ChuanHoa.ChuanHoa(txtHoNhanVien.getText()));
            txtTenNhanVien.setText(ChuanHoa.ChuanHoa(txtTenNhanVien.getText()));
            if (txtHoNhanVien.getText().equals("") && txtPhaiNhanVien.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                return;
            }
            for (int i=0;i<txtTenNhanVien.getText().length();++i){
                if (txtTenNhanVien.getText().charAt(i)==' '){
                    JOptionPane.showMessageDialog(null,"Tên nhân viên chỉ nhận một chữ, vui lòng nhập lại!");
                    return;
                }
            }
            int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc chỉnh sữa");
                if(result == 0){
                    checkEditNhanVien = false;
                    GiaoDienQuanLy.changeNhanVien(txtMaNhanVien.getText(),txtHoNhanVien.getText(),txtTenNhanVien.getText(),txtPhaiNhanVien);
                    this.dispose();
                }
        }
        if(e.getSource() == JBTPhaiNam){
            JBTPhaiNam.setBackground(new Color(42, 185, 48));
            JBTPhaiNu.setBackground(new Color(66, 66, 66));
            txtPhaiNhanVien = "Nam";
        }
        if(e.getSource() == JBTPhaiNu){
            JBTPhaiNu.setBackground(new Color(42, 185, 48));
            JBTPhaiNam.setBackground(new Color(66, 66, 66));
            txtPhaiNhanVien = "Nữ";
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == txtTenNhanVien)  //can fix them
            if ((((e.getKeyCode()>=65 && e.getKeyCode()<=90) || e.getKeyCode()==0
                    || e.getKeyCode() == 32 || e.getKeyCode() == 16) && txtTenNhanVien.getText().length() < 45) || e.getKeyCode() == 8 || e.getKeyCode() == 127) {
                txtTenNhanVien.setEditable(true);
            } else txtTenNhanVien.setEditable(false);

        if (e.getSource() == txtHoNhanVien) {
            if ((((e.getKeyCode()>=65 && e.getKeyCode()<=90) || e.getKeyCode()==0
                    || e.getKeyCode() == 32 || e.getKeyCode() == 16) && txtHoNhanVien.getText().length() < 45) || e.getKeyCode() == 8 || e.getKeyCode() == 127) {
                txtHoNhanVien.setEditable(true);
            } else txtHoNhanVien.setEditable(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public static boolean getCheckEditNhanVien(){
        return checkEditNhanVien;
    }
}