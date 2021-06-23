package Model;
import java.util.*;

/**
 * 
 */
public interface IPost {
    public void notif();
    public void addTaggedFriend(IUser user);
    public void removeTaggedFriend(IUser user);

}