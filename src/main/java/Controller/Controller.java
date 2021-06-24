package Controller;
import Database.ConnectionDB;
import Database.Data.PostsData;
import Model.Post;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    private void copyFile(String pathFrom, String pathTo){
        File source = new File(pathFrom);
        File dest = new File(pathTo);
        if(!source.exists()){
            source.mkdirs();
        }
        if(!dest.exists()){
            dest.mkdirs();
        }
        try {
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Post> laydulieu(HttpServletRequest request){
        File file = new File("");
        copyFile(file.getAbsolutePath() + "/images/posts", request.getServletContext().getRealPath("") +  "/images/posts");
        return  PostsData.getAllPost();
    }



}