import java.sql.*;
import org.postgresql.*;

public class Test {
    public static void main(String[] args) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "mediasalpes", "chateauroux915");
        System.out.println(c.getSchema());
    }
}
