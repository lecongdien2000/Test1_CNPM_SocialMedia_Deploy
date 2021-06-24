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



    public List<Post> laydulieu(HttpServletRequest request){
        List<Post> posts = PostsData.getAllPost();
        for(Post post: posts){
            List<String> medias = new ArrayList<>();
            medias.addAll(post.content.images);
            medias.addAll(post.content.videos);
            for(String mediaPath: medias){
                File file = new File(request.getServletContext().getRealPath("") + "/images/posts");
                if(!file.exists()) file.mkdirs();
                String desPath = request.getServletContext().getRealPath("") + mediaPath;
                file = new File(desPath);
                if(!file.exists())
                    PostsData.getData(mediaPath, desPath);
            }
        }

         return  PostsData.getAllPost();
    }



}