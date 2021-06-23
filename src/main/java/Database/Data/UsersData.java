package Database.Data;
import Database.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Model.*;

public class UsersData {


    public static User getUsers(String userID){
        List<String> values = new ArrayList<>();
        values.add(userID);
        return getPreparedDataQuery("SELECT * FROM user WHERE userID like ?", values).get(userID);
    }

//    public static User getUsers(String uname, String pass){
//        List<String> values = new ArrayList<>();
//        values.add(uname);
//        values.add(pass);
//        return getPreparedDataQuery("SELECT * FROM user WHERE username like ? and password like ?", values).get(uname);
//    }

    public static HashMap<String, User> getPreparedDataQuery(String query, List<String> values){
        HashMap<String, User> userResultList = new HashMap<>();
        try {
            PreparedStatement preStat = null;
            preStat = ConnectionDB.connect(query);
            for(int i = 0; i < values.size(); i++){
                preStat.setString(i+1, values.get(i));
            }
            ResultSet rs = preStat.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getString("userID"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fullName"));
                user.setDateCreated(Date.convertSqlStringToDate(rs.getDate("dateCreated").toString(), rs.getTime("dateCreated").toString())); //return sql type///"/.;'./'
                userResultList.put(user.getId(), user);
            }

            rs.close();
            preStat.close();
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        return userResultList;
    }
    //chinh lai id user xuat hien
//    public static void insertUser(User user) {
//        try {
//            PreparedStatement state1 = ConnectionDB.connect("insert into user(userID, fullName, username, password, dateCreated)" +
//                    " values(?, ?, ?, ?, ?)");
//            state1.setString(1, user.getUsername()); //insert user id
//            state1.setString(2, user.getFullname());
//            state1.setString(3, user.getUsername());
//            state1.setString(4, user.getPassword());
//            state1.setDate(5, java.sql.Date.valueOf(user.getDateCreated().convertDateToSqlString()));
//            state1.executeUpdate();
//            state1.close();
//        }catch(ClassNotFoundException|SQLException e){
//            e.printStackTrace();
//        }
//    }

    //    public static void updateUser(User user) {
//        try {
//            PreparedStatement state1 = ConnectionDB.connect("update user set password = ?, email = ?, gender = ?, birthday = ?, description = ?, accountName = ?, admin = ? where username = ?");
//            state1.setString(1, user.getPassword());
//            state1.setString(2, user.getEmail());
//            state1.setString(3, user.getGender());
//            state1.setDate(4, java.sql.Date.valueOf(user.getBirthday().convertDateToSqlString()));
//            state1.setString(5, user.getDescription());
//            state1.setString(6, user.getAccountName());
//            state1.setBoolean(7, user.isAdmin());
//            state1.setString(8, user.getUsername()); //where username = ...
//            state1.executeUpdate();
//            state1.close();
//        }catch(ClassNotFoundException|SQLException e){
//            e.printStackTrace();
//        }
//    }
    public static List<User> getAllUser(){
        List<User> result = new ArrayList<>();
        try {
            Statement statement = null;
            statement = ConnectionDB.connect();
            ResultSet rs = statement.executeQuery("SELECT * FROM user");
            while(rs.next()){
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getString("userID"));
                user.setDateCreated(Date.convertSqlStringToDate(rs.getDate("dateCreated").toString(), rs.getTime("dateCreated").toString())); //return sql type///"/.;'./'
                result.add(user);
            }

            rs.close();
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<User> getUsersWithKeyword(String key){
        if(key==null || key.equals(""))
            return UsersData.getAllUser();

        List<User> result = new ArrayList<>();
        try {
            Statement statement = null;
            statement = ConnectionDB.connect();
            ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE username like '%" + key +"%'" + "or accountName like'%" + key + "%'" );
            while(rs.next()){
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getString("userID"));
                user.setDateCreated(Date.convertSqlStringToDate(rs.getDate("dateCreated").toString(), rs.getTime("dateCreated").toString())); //return sql type///"/.;'./'
                result.add(user);
            }

            rs.close();
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}