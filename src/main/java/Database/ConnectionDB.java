package Database;

import java.sql.*;

public class ConnectionDB {
    final static String HOST_NAME = "btyfejfcrprsofbzxwaf-mysql.services.clever-cloud.com";
    final static String PORT = "3306";
    final static String DATABASE_NAME = "btyfejfcrprsofbzxwaf";
    final static String USERNAME = "ubgy5aone8fvp7ng";
    final static String PASSWORD = "IIu41pi9g7ShMABEP1GR";

//    final static String HOST_NAME = "localhost";
//    final static String PORT = "3306";
//    final static String DATABASE_NAME = "database_social";
//    final static String USERNAME = "root";
//    final static String PASSWORD = "";


    static String url = "jdbc:mysql://"+HOST_NAME+":"+PORT+"/"+DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
    static Connection connection;

    public static Statement connect() throws ClassNotFoundException, SQLException {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,
                    USERNAME, PASSWORD);
        }
        return connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }

    public static PreparedStatement connect(String sql) throws ClassNotFoundException, SQLException {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,
                    USERNAME, PASSWORD);
        }
        return connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }

    public static void closeConnection() throws SQLException {
        if (!connection.isClosed())
            connection.close();
    }

    public void laydulieu(){
        try {
            Statement statement = ConnectionDB.connect();
            String sql="Select user.fullname, post.text, post.dateCreated, media.mediaPath from post,media,user where post.postID=media.postID and post.userID=user.userID";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String fname =resultSet.getString("fullname");
                String text = resultSet.getString("text");
                String mediapath = resultSet.getString("mediaPath");
                String datecreated = resultSet.getString("dateCreated");
                System.out.println("name" + fname + "text" + text + "link" + mediapath + "date" + datecreated);

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}