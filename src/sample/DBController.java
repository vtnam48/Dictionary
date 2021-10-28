package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DBController {
    private static String url = "jdbc:mysql://localhost:3306/dictionarydata?useUnicode=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String pass = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return connection;
    }

    public static void insertEV(String target, String explain) {
        String query = "insert into entovie(word_target,word_explain) values(?,?)";

        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, target);
            pstmt.setString(2, explain);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertVE(String target, String explain) {
        String query = "insert into vietoen(word_target,word_explain) values(?,?)";

        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, target);
            pstmt.setString(2, explain);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEV(String target) {
        String query = "delete from entovie where word_target=?";

        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, target);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVE(String target) {
        String query = "delete from vietoen where word_target=?";

        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, target);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEV(String target, String explain) {
        String query = "update entovie set word_target=?,word_explain=? where word_target='" + target + "'";

        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, target);
            pstmt.setString(2, explain);
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateVE(String target, String explain) {
        String query = "update vietoen set word_target=?,word_explain=? where word_target='" + target + "'";

        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, target);
            pstmt.setString(2, explain);
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkExitEV(String target) {
        String query = "SELECT COUNT(*) FROM entovie WHERE word_target = '" + target + "'";

        int kq = 0;
        try {
            Connection connection = getConnection();
            Statement pstmt = connection.createStatement();
            ResultSet rs = pstmt.executeQuery(query);

            if(rs.next()) {
                kq = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (kq == 0) {
            return false;
        }

        return true;
    }

    public static boolean checkExitVE(String target) {
        String query = "SELECT COUNT(*) FROM vietoen WHERE word_target = '" + target + "'";

        int kq = 0;
        try {
            Connection connection = getConnection();
            Statement pstmt = connection.createStatement();
            ResultSet rs = pstmt.executeQuery(query);

            if(rs.next()) {
                kq = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (kq == 0) {
            return false;
        }

        return true;
    }

}
