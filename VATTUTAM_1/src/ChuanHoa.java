import java.util.Arrays;
import java.util.Locale;

public class ChuanHoa {
    public static void main(String[] args) {
        String s = "xin chào bạn nhé KAKAKA fC  ";
        s = s.trim();
        System.out.println("s =" + s);
        String[] Arr = s.split("");
        if (s.length() > 0) {
            s = Arr[0].toUpperCase();
        }

        for (int i = 1; i < Arr.length - 1; i++) {
            if (Arr[i - 1].equals(" ")) {
                System.out.println("HAHA");
                s += Arr[i].toUpperCase();
            } else {
                System.out.println("HAHA");
                s += Arr[i];
            }
        }
        s += Arr[Arr.length - 1];

        System.out.println("s = " + s);
    }

    public static String ChuanHoa(String s) {
        s = s.trim();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == ' ' && s.charAt(i + 1) == ' ') {
                s = s.substring(0, i) + s.substring(i + 1);
                i--;
            }
        }

        String Arr[] = s.split("");
        if (s.length() > 0) {
            s = Arr[0].toUpperCase();
        }

        for (int i = 1; i < Arr.length - 1; i++) {
            if (Arr[i - 1].equals(" ")) {
                System.out.println("HAHA");
                s += Arr[i].toUpperCase();
            } else {
                System.out.println("HAHA");
                s += Arr[i];
            }
        }
        s += Arr[Arr.length - 1];
        return s;
    }

    public static String ChuyenSoThanhTien(String s){
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
        return temp;
    }

    public static long ChuyenTienSangSo(String s){
        String temp = "";
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i) != ',' && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                temp += s.charAt(i);
            }
        }
        return Long.parseLong(temp);
    }

}


