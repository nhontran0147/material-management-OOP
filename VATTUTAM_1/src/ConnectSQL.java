import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectSQL {
    private static Connection con;

    public ConnectSQL() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("111111");
    //    ds.setServerName("LAPTOP-TC17PAIU\\SQLEXPRESS");
        ds.setServerName("NHONTRAN");
//        ds.setServerName("26.34.17.62");
        ds.setPortNumber(1433);
        ds.setDatabaseName("QuanLyVatTu");
        try {
            con = ds.getConnection();
            System.out.println("SUCCESS");
            System.out.println(con.getMetaData());
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getCon() {
        return con;
    }
}
