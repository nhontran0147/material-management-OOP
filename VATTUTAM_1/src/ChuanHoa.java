import java.util.Arrays;
import java.util.Locale;

public class ChuanHoa {
    public static void main(String[] args) {
        String s = "   z  ";
        System.out.println(ChuanHoa(s));
    }

    public static String ChuanHoa(String s) {
        s = s.trim();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == ' ' && s.charAt(i + 1) == ' ') {
                s = s.substring(0, i) + s.substring(i + 1);
                i--;
            }
        }
        s=s.toLowerCase();
        String Arr[] = s.split("");
        String stringRes=Arr[0].toUpperCase();
        for (int i = 1; i < Arr.length; i++) {
            if (Arr[i - 1].equals(" ")) {
                stringRes += Arr[i].toUpperCase();
            } else {
                stringRes += Arr[i];
            }
        }
        return stringRes;
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


