import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    static String driver = "oracle.jdbc.driver.OracleDriver";
    static String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
    static String db_user = "YARIX";
    static String db_pass = "1111";
    static Connection connection = null;

    public static Connection getConnection() {

        try {
            if (connection == null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(db_url, db_user, db_pass);
            } else return connection;
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        return connection;
    }

}
