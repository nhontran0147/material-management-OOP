import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class EditHoaDon extends JFrame implements ActionListener, KeyListener {
    private final int WIDTH_EDIT = 400;
    private final int HEIGHT_EDIT = 450;
    private JPanel JPmain;
    private JLabel JLBTop;
    private JButton btnEdit;
    private JLabel JLBVatTu;
    private JLabel JLBSoLuong;
    private JTextField txtSoLuong;
    private JLabel JLBDonGia;
    private JTextField txtDonGia;
    private JLabel JLBVat;
    private JTextField txtVat;
    private String maVT;
    private String conLai;
    private String loaiHD;
    private static int dangEdit = 0;

    public EditHoaDon(String MaVT, String TenVT, String ConLai, String LoaiHD, String SoLuong, String DonGia, String Vat) {
        this.setTitle("CHỈNH SỬA MẶT HÀNG");
        this.setSize(WIDTH_EDIT, HEIGHT_EDIT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        dangEdit = 1;

        JPmain = new JPanel();
        JPmain.setBounds(0, 0, WIDTH_EDIT, HEIGHT_EDIT);
        JPmain.setLayout(null);
        JPmain.setBackground(new Color(255, 255, 255));

        JLBTop = new JLabel("CHỈNH SỬA MẶT HÀNG");
        JLBTop.setBackground(new Color(33, 168, 109));
        JLBTop.setOpaque(true);
        JLBTop.setSize(WIDTH_EDIT, 70); // height 70
        JLBTop.setHorizontalAlignment(SwingConstants.CENTER);
        JLBTop.setFont(new Font("Ubuntu", Font.BOLD, 20)); //Helvetica
        JLBTop.setForeground(Color.WHITE);
        JPmain.add(JLBTop);

        JLBVatTu = new JLabel(new String("Mã VT " +MaVT+" "+TenVT+" còn lại: "+ConLai).toUpperCase());
        JLBVatTu.setHorizontalAlignment(SwingConstants.CENTER);
        JLBVatTu.setOpaque(true);
        JLBVatTu.setBounds(30, 90, 330, 30);
        JLBVatTu.setFont(new Font("Ubuntu", Font.BOLD, 16)); //Helvetica

        JLBSoLuong = new JLabel("SO LUONG: ");
        JLBSoLuong.setBounds(30, 140, 140, 40); // height 120
        JLBSoLuong.setBackground(new Color(36, 169, 111));
        JLBSoLuong.setForeground(Color.WHITE);
        JLBSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
        JLBSoLuong.setOpaque(true);

        JLBDonGia = new JLabel("ĐƠN GIA: ");
        JLBDonGia.setBounds(30, 190, 140, 40); // height = 160
        JLBDonGia.setBackground(new Color(36, 169, 111));
        JLBDonGia.setForeground(Color.WHITE);
        JLBDonGia.setOpaque(true);
        JLBDonGia.setHorizontalAlignment(SwingConstants.CENTER);

        JLBVat = new JLabel("PHÍ VAT: ");
        JLBVat.setBounds(30, 240, 140, 40); // height 120
        JLBVat.setBackground(new Color(36, 169, 111));
        JLBVat.setForeground(Color.WHITE);
        JLBVat.setHorizontalAlignment(SwingConstants.CENTER);
        JLBVat.setOpaque(true);

        JPmain.add(JLBSoLuong);
        JPmain.add(JLBVat);
        JPmain.add(JLBDonGia);
        JPmain.add(JLBVatTu);

        txtSoLuong = new JTextField();
        txtSoLuong.setBounds(190, 140, 170, 40); // 150, 50
        txtSoLuong.setBackground(new Color(102, 195, 239));
        txtSoLuong.setForeground(new Color(255, 255, 255));
        txtSoLuong.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtSoLuong.setFont(new Font("Arial", Font.BOLD, 15));
        JPmain.add(txtSoLuong);

        txtDonGia = new JTextField();
        txtDonGia.setBounds(190, 190, 170, 40); // 160
        txtDonGia.setBackground(new Color(102, 195, 239));
        txtDonGia.setForeground(new Color(255, 255, 255));
        txtDonGia.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtDonGia.setFont(new Font("Arial", Font.BOLD, 15));
        JPmain.add(txtDonGia);

        txtVat = new JTextField();
        txtVat.setBounds(190, 240, 170, 40); // 160
        txtVat.setBackground(new Color(102, 195, 239));
        txtVat.setForeground(new Color(255, 255, 255));
        txtVat.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtVat.setFont(new Font("Arial", Font.BOLD, 15));
        JPmain.add(txtVat);



        btnEdit = new JButton("EDIT & LƯU");
        btnEdit.setBounds((WIDTH_EDIT - 100) / 2, 330, 100, 40); // 220
        btnEdit.setBackground(new Color(96, 100, 191));
        btnEdit.setBorder(null);
        btnEdit.setFocusable(false);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.addActionListener(this);
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPmain.add(btnEdit);

        maVT = MaVT;
        conLai = ConLai;
        loaiHD = LoaiHD;
        txtVat.setText(Vat);
        txtSoLuong.setText(SoLuong);
        txtDonGia.setText(DonGia);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                dangEdit = 0;
            }
        });

        this.add(JPmain);
        this.addKeyListener(this);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (txtSoLuong.getText().equals("") || txtSoLuong.getText().equals("") || txtVat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
        }
        else if(loaiHD.equals("Xuất") && Integer.parseInt(txtSoLuong.getText()) > Integer.parseInt(conLai)){
            JOptionPane.showMessageDialog(null, "Số lượng vượt quá số lượng tồn kho");
            return;
        }else{
            int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc chỉnh sữa");
            if(result == 0){
                JPanelHoaDon.upDateMatHangEdit(maVT, txtSoLuong.getText(), txtDonGia.getText(), txtVat.getText());
                JOptionPane.showMessageDialog(null, "Chỉnh sửa mặt hàng thành công");
                dangEdit = 0;
                this.dispose();
            }

        };
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

    public static int getDangEdit() {
        return dangEdit;
    }
}

class TEST_EDIT_HD {
    public static void main(String[] args) {
        new EditHoaDon("1000", "Xi Măng", "64800", "Xuất", "9000", "363636", "2");
    }
}

