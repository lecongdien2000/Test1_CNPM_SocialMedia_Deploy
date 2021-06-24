package Database.Data;
import Database.ConnectionDB;
import Model.*;
import Model.Date;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Database.ConnectionDB.connect;

public class PostsData {

    public static void insertPost(Post post){
        try {
            PreparedStatement state1 = connect("insert into post" +
                    " values(?, ?, ?, ?)");
            state1.setString(1, post.id); //insert user id
            state1.setString(2, post.user.id);
            state1.setString(3, post.content.text);

            //ttest
            System.out.print(post.content.text);


            state1.setTimestamp(4, java.sql.Timestamp.valueOf(post.date.convertDateTimeToSqlString()));
            state1.executeUpdate();
            state1.close();
        }catch(ClassNotFoundException| SQLException e){
            e.printStackTrace();
        }
        if(!post.content.isEmptyMedia()) insertMedia(post);
    }

    private static void insertMedia(Post post) {
        for(String img: post.content.images){
            insertMedia(post.id, img);
        }
        for(String video: post.content.videos){
            insertMedia(post.id, video);
        }
    }

    private static void insertMedia(String postID, String mediaPath){
        try {
            PreparedStatement state1 = connect("insert into media" +
                    " values(?, ?)");
            state1.setString(1, postID); //insert user id
            state1.setString(2, mediaPath);
            state1.executeUpdate();
            state1.close();
        }catch(ClassNotFoundException| SQLException e){
            e.printStackTrace();
        }
    }



    public static int getSize() {
        List<User> result = new ArrayList<>();
        int size =0;
        try {
            Statement statement = null;
            statement = connect();
            ResultSet rs = statement.executeQuery("SELECT * FROM post");
            if (rs != null)
            {
                rs.last();
                size = rs.getRow();
            }

            rs.close();
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return size;
    }

    public static List<Post> getAllPost() {
        List<String> values = new ArrayList<>();
        return new ArrayList<Post>(getPreparedDataQuery("SELECT * FROM post", values).values());
    }


    public static Post getPost(String postID) {
        List<String> values = new ArrayList<>();
        values.add(postID);
        return getPreparedDataQuery("SELECT * FROM post WHERE postID like ?", values).get(postID);
    }
    public static HashMap<String, Post> getPreparedDataQuery(String query, List<String> values){
        HashMap<String, Post> postResultList = new HashMap<>();
        try {
            PreparedStatement preStat = null;
            preStat = connect(query);
            for(int i = 0; i < values.size(); i++){
                preStat.setString(i+1, values.get(i));
            }
            ResultSet rs = preStat.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.id = rs.getString("postID");
                post.user = UsersData.getUsers(rs.getString("userID"));
                post.content.text = rs.getString("text");
                post.content.images = MediasData.getImgs(rs.getString("postID"));
                post.content.videos = MediasData.getVideos(rs.getString("postID"));
                post.date = Date.convertSqlStringToDate(rs.getDate("dateCreated").toString(), rs.getTime("dateCreated").toString());
                postResultList.put(post.id, post);
            }
            rs.close();
            preStat.close();
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        return postResultList;
    }

    public static void saveData(String dbPath, String filePath) {
        String sql = "insert into data values (?, ?)";
        PreparedStatement stat = null;
        try {
            stat = connect(sql);
            //create file and connect to stream
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            stat.setString(1, dbPath);
            stat.setBinaryStream(2, fis);
            stat.executeUpdate();
            stat.close();
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void getData(String dbPath, String desPath) {
        String sql = "select * from data where path='" + dbPath + "'";
        Statement stat = null;
        try {
            stat = connect();
            ResultSet rs = stat.executeQuery(sql);
            //Create file
            FileOutputStream fos = new FileOutputStream(desPath);
            //read rs data
            if(rs.next()) {
                String path = rs.getString(1);
                InputStream is = rs.getBinaryStream(2);
                byte[] buffer = new byte[1024*1024];
                while(is.read(buffer)>0) {
                    fos.write(buffer);
                }
                fos.flush();
                is.close();
                fos.close();
            }
            stat.close();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}