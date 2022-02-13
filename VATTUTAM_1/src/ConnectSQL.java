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
        ds.setServerName("NHONTRAN");
        ds.setPortNumber(1433);
        ds.setDatabaseName("QuanLyVatTu");
        try {
            con = ds.getConnection();

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
