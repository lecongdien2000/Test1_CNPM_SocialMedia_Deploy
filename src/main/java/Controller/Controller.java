package Controller;
import Database.ConnectionDB;
import Model.PostCreated;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


/**
 * 
 */
public class Controller {

    /**
     * Default constructor
     */
    public Controller() {
    }

    /**
     * @param postID
     */
    public void loadListComments(String postID) {
        // TODO implement here
    }

    /**
     * @param text 
     * @return
     */
    public boolean checkLength(String text) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean checkAlreadyLoaded() {
        // TODO implement here
        return false;
    }

    /**
     * @param mediaPath 
     * @return
     */
    public boolean checkSize(String mediaPath) {
        // TODO implement here
        return false;
    }

    /**
     * @param text 
     * @param mediaPath 
     * @return
     */
    public boolean checkEmptyComment(String text, String mediaPath) {
        // TODO implement here
        return false;
    }

    /**
     * @param userID 
     * @param postID 
     * @param text 
     * @param mediaPath
     */
    public void postComment(String userID, String postID, String text, String mediaPath) {
        // TODO implement here
    }


    public boolean post(String text, List<String> listPicture, List<String> listVideo) {
        // TODO implement here
        return false;
    }

    /**
     * @param postID 
     * @param userID
     */
    public void like(String postID, String userID) {
        // TODO implement here
    }

    public ArrayList<PostCreated> laydulieu(){
        ArrayList<PostCreated> listarr = new ArrayList<PostCreated>();
        try {
            Statement statement = ConnectionDB.connect();
            String sql="Select user.fullname, post.text, post.dateCreated, media.mediaPath from post,media,user where post.postID=media.postID and post.userID=user.userID";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String fname =resultSet.getString("fullname");
                String text = resultSet.getString("text");
                String mediapath = resultSet.getString("mediaPath");
                String datecreated = resultSet.getString("dateCreated");
                PostCreated postCreated  = new PostCreated(fname,text,mediapath,datecreated);
                listarr.add(postCreated);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  listarr;
    }



}