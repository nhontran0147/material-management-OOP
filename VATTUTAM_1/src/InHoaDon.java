import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.*;
import java.text.MessageFormat;
import java.util.Arrays;
import javax.swing.table.DefaultTableCellRenderer;


public class InHoaDon extends JFrame{
    private JPanel panel_print;
    private static JTable tbhoadon;
    private static JTextArea abcd;



    public InHoaDon() {
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);


        String[] columnJT2 = {"MAVT", "TENVT", "SOLUONG", "DONGIA", "VAT", "THANHTIEN"};
        Object[][] dataJT2 = {{1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
                {1000, "Xi Măng", 20000, 999999999, 0.01, 999999999},
        };


        DefaultTableModel modelHoaDon = new DefaultTableModel(dataJT2, columnJT2);
        tbhoadon = new JTable(modelHoaDon);
        tbhoadon.setFillsViewportHeight(true);
        tbhoadon.setBackground(new Color(255, 255, 255));
        tbhoadon.setOpaque(true);
        tbhoadon.setShowGrid(false); // Tắt kẻ bảng
        tbhoadon.setShowHorizontalLines(false); // Tắt kẻ ngang
        tbhoadon.setShowVerticalLines(false); // Tắt kẻ dọc
        tbhoadon.setForeground(new Color(0, 123, 255));
        tbhoadon.setFont(new Font("SansSerif", Font.PLAIN, 15));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbhoadon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Chữ ra giữa bảng
        tbhoadon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbhoadon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbhoadon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbhoadon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbhoadon.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbhoadon.setRowHeight(30);
        tbhoadon.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16)); // Set font Header
        tbhoadon.getTableHeader().setPreferredSize(new Dimension(200, 40)); // Set height
        tbhoadon.getTableHeader().setBackground(new Color(121, 195, 70));
        tbhoadon.getTableHeader().setForeground(Color.WHITE);
        tbhoadon.setSelectionBackground(Color.YELLOW);


//        JScrollPane JS2 = new JScrollPane(tbhoadon);
//        JS2.setBounds(0, 50, 1300, 700); // w: 700 bg: 100
        tbhoadon.setBounds(0, 50, 400, 700); // w: 700 bg: 100
        tbhoadon.setFocusable(false); // Không tạo focus 1 phần tử
        tbhoadon.setDefaultEditor(Object.class, null); // Không cho edit table

        JLabel abc = new JLabel("HÓA ĐƠN MUA HÀNG");
        abc.setBounds(0, 1000, 1000, 50);
        abc.setHorizontalAlignment(SwingConstants.CENTER);
        abc.setBackground(new Color(33, 205, 65));
        abc.setOpaque(true);

        panel_print = new JPanel();
        panel_print.setBounds(0, 0, 1000, 700);
        panel_print.setLayout(null);
        panel_print.add(abc);

        abcd = new JTextArea();

        abcd.append("XIN CHÀO");
        setText();
        try {
            abcd.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }

        JScrollPane ab = new JScrollPane(panel_print);
        ab.setBounds(0, 0, 1000, 700);
        ab.setBackground(Color.BLUE);


//        panel_print.add(tbhoadon);
        this.add(ab);

        this.setVisible(true);
    }

    public void PRINTHOADON(){

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");

        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum > 0){
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D)pg;

                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.47,0.47);

                abcd.print(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try{

                job.print();
            }
            catch (PrinterException ex){
                ex.printStackTrace();
            }


        }
    }

    public static JTable getTbhoadon() {
        return tbhoadon;
    }

    public void setText(){
        for (int i = 0; i < InHoaDon.getTbhoadon().getRowCount(); i++) {  // Loop through the rows
            abcd.append("|-------------------SỐ HÓA ĐƠN: "+InHoaDon.getTbhoadon().getValueAt(i, 0) +"\n");
//            System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 0));
//            System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 1));
//            System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 2));
//            System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 3));
//            System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 4));
//            System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 5));

        }
    }
}

//class MainPrint{
//    public static void main(String[] args) {
//        InHoaDon A = new InHoaDon();
////        MessageFormat header = new MessageFormat("Page {0,number,integer}");
////        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
////        try {
////            InHoaDon.getTbhoadon().print(JTable.PrintMode.NORMAL, header, footer);
////        } catch (PrinterException e) {
////            e.printStackTrace();
////        }
////        A.PRINTHOADON();
//
//
//            for (int i = 0; i < InHoaDon.getTbhoadon().getRowCount(); i++) {  // Loop through the rows
//                System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 0));
//                System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 1));
//                System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 2));
//                System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 3));
//                System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 4));
//                System.out.println(InHoaDon.getTbhoadon().getValueAt(i, 5));
//
//            }
//    }
//}
//
