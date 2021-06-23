package Database;
import Model.Comment;
import Model.Post;
import Database.Data.*;
import java.util.*;

public class Database {

    public Database() {
    }

    public static List<Comment> getListComments(String postID) {
        // TODO implement here
        return null;
    }

    public static void loadMedia(String mediaPath) {
        // TODO implement here
    }

    public static void insertComment(Comment cmt) {
        // TODO implement here
    }

    public static void insertPost(Post p) {
        checkData(p);
        PostsData.insertPost(p);
    }
    public static void increaseLike(String postID) {
        // TODO implement here
    }
    public static void decreaseLike(String postID) {
        // TODO implement here
    }
    public static Post getPost(String postID) {
        return PostsData.getPost(postID);
    }
    public static void checkData(Post post) {
        // TODO implement here
    }
    public static String generateID(){
        return PostsData.getSize() + "";
    }

}