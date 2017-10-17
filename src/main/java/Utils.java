import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static List<String> pathSqlScriptsList = new ArrayList<>();
    private static final String PATH_TO_FILE = "src\\main\\resources\\properties\\path";


    private static List<String> readScripts() {
        List<String> scripts = new ArrayList<>();
        try {
            Scanner scnr = new Scanner(new File(PATH_TO_FILE)).useDelimiter(";");
            while (scnr.hasNext()) {
                String path = scnr.nextLine();
                pathSqlScriptsList.add(path);
                Scanner scnrPaths = new Scanner(new File(path));
                scripts.add(scnrPaths.nextLine());
                scnrPaths.close();
            }
            scnr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scripts;
    }


    public static List<ResultSet> runScripts() {
        List<ResultSet> resultSetList = new ArrayList<>();
        ResultSet rs = null;
        for (String sql : Utils.readScripts()) {
            Connection connection = DbConnection.getConnection();
            Statement st = null;
            try {
                st = connection.createStatement();
                rs = st.executeQuery(sql.replaceAll("%s", "20166"));
                resultSetList.add(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultSetList;
    }
}

