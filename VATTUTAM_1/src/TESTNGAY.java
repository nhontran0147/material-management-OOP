import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TESTNGAY extends JFrame implements ActionListener {
    JDateChooser dateChooser;
    JButton JLB01;


    public TESTNGAY(){
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);

        JLB01 = new JButton("XEM NGÀY");
        JLB01.setBounds(20, 100, 100, 30);
        JLB01.addActionListener(this);


        Calendar cl = Calendar.getInstance();
        dateChooser = new JDateChooser(cl.getTime());
        dateChooser.setBounds(20, 20, 150, 30); // Modify depending on your preference
        dateChooser.setForeground(new Color(102, 195, 239));
        dateChooser.setOpaque(true);
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setFont(new Font("Arial", Font.BOLD, 15));
        this.add(dateChooser);
        this.add(JLB01);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TESTNGAY();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("HAHA");
        System.out.println("DATE: "+dateChooser.getDate());
        System.out.println("HÍ: "+(dateChooser.getDate().getTime() - Calendar.getInstance().getTime().getTime()) / (60*60*24*1000));
        if(dateChooser.getDate() == null){
            JOptionPane.showMessageDialog(null, "SAI NGÀY RÙI CU");
        }

        else if(dateChooser.getDate() != null){
            SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
            String d = fm.format(Calendar.getInstance().getTime());
            System.out.println("A = "+d);
            try {
                Date B = fm.parse(d);
                System.out.println("B = "+B.getTime());
                if(dateChooser.getDate().getTime() - B.getTime() > 0){
                    JOptionPane.showMessageDialog(null, "NHẬP LỘN NGÀY MAI NGÀY MỐT RÙI");
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
                ex.printStackTrace();
            }
        }
        else {

            SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
            String d = fm.format(dateChooser.getDate());
            System.out.println("D = "+d);

            Date PRO = dateChooser.getDate();
            String xx = fm.format(PRO);

            Calendar PROO = Calendar.getInstance();
            System.out.println("PROO1 = "+PROO.getTime());
            PROO.setTime(PRO);
            System.out.println("PROO2 = "+PROO.getTime());

            // Format = cách chuyển cal => date (cal.getTime)


            String X = "23/03/2001";

            try {
                Date Y = fm.parse(X);
                dateChooser.setDate(Y);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }


            String DateOne = "01/12/2021";
            String DateTwo = "31/12/2021";
            try {
                Date A1 = fm.parse(DateOne);
                Date A2 = fm.parse(DateTwo);
                Calendar C3 = Calendar.getInstance();
                Date A3 = C3.getTime();
                System.out.println("KHOẢNG CÁCH NGÀY = "+(A2.getTime() - A1.getTime()) / (1000 * 60 * 60 * 24));
                System.out.println("KHOẢNG CÁCH NGÀY 2 = "+(A3.getTime() - A1.getTime()));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class HEHEHE{
    public static void main(String[] args) {
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        String d = fm.format(Calendar.getInstance().getTime());
        System.out.println("A = "+d);
        try {
            Date B = fm.parse(d);
            System.out.println("B = "+B.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(Calendar.getInstance().getTime().getTime());
    }
}

class HOHO{
    public static void main(String[] args) {
        String s = "662662";
        String temp = "";
        int pos = s.length() % 3 - 1;
        if(pos < 0){
            pos = 2;
        }
        for(int i = 0; i<s.length(); i++)
        {
            temp += s.charAt(i);
            if(pos == i){
                if(i != s.length() - 1)
                    temp += ',';
                pos+=3;
            }
        }
        System.out.println(temp);
//        String temp = "###,###.##";
//        DecimalFormat dc = new DecimalFormat();
//        System.out.format("Number after format = "+ dc.format(s));

        System.out.println( (long) (122222555) * (13433 * (1 + 1555) / 100));
    }
}

class TEST_1234{
    public static void main(String[] args) {
        System.out.println(String.format("%-10s%-12s%-15s%-12s%n", "ABC", "CDEF", "MNPQH", "KSJJSJS"));
        long x = 3333 * (long) (1111 * (100 + 6)) / 100;
        System.out.println("x = "+x);


    }
}
