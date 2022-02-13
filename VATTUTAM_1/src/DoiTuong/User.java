package DoiTuong;

public class User {
    String userName;
    String passWord;
    int quyen;
    public User(String userName, String passWord, int quyen){
        this.userName = userName;
        this.passWord = passWord;
        this.quyen = quyen;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }


    public int getQuyen() {
        return quyen;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
