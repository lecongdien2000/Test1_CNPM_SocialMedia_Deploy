package Database.Data;

import Database.ConnectionDB;
import Model.Content;
import Model.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MediasData {
    //can't find anything
    public static List<String> getImgs(String postID) {
        return getFiles(postID, Content.imgTypes);
    }

    private static List<String> getDataQuery(String query) {
        List<String> result = new ArrayList<>();
        try {
            Statement statement = null;
            statement = ConnectionDB.connect();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                result.add(rs.getString("mediaPath"));
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

    public static List<String> getVideos(String mediaID) {
        return getFiles(mediaID, Content.videoTypes);
    }

    private static List<String> getFiles(String postID, String[] types){
        List<String> values = new ArrayList<>();
        String sql = "SELECT * FROM media WHERE postID like "+ postID ;
        if(types.length == 0) return getDataQuery(sql);
        sql+=" AND ( mediaPath like '%." + types[0] + "'";
        for(int i = 1; i < types.length; i++){
            sql+=" OR mediaPath like '%." + types[i] + "'";
        }
        sql+=")";
        return getDataQuery(sql);
    }

}
