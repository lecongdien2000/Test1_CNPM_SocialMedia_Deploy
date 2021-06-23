package Database.Data;
import Database.ConnectionDB;
import Model.*;
import Model.Date;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostsData {

    public static void insertPost(Post post){
        try {
            PreparedStatement state1 = ConnectionDB.connect("insert into post" +
                    " values(?, ?, ?, ?)");
            state1.setString(1, post.id); //insert user id
            state1.setString(2, post.user.id);
            state1.setString(3, post.content.text);
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
            PreparedStatement state1 = ConnectionDB.connect("insert into media" +
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
            statement = ConnectionDB.connect();
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


    public static Post getPost(String postID) {
        List<String> values = new ArrayList<>();
        values.add(postID);
        return getPreparedDataQuery("SELECT * FROM post WHERE postID like ?", values).get(postID);
    }
    public static HashMap<String, Post> getPreparedDataQuery(String query, List<String> values){
        HashMap<String, Post> postResultList = new HashMap<>();
        try {
            PreparedStatement preStat = null;
            preStat = ConnectionDB.connect(query);
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
}