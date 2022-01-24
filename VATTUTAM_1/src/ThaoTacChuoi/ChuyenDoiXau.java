package ThaoTacChuoi;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ChuyenDoiXau {
    public static String removeAccent(String s) {
        s=s.toLowerCase();
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
